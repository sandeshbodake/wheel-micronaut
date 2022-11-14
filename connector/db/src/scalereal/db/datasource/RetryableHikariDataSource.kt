package scalereal.db.datasource

import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.Context
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory
import scalereal.db.datasource.config.DatabaseConfiguration
import scalereal.db.datasource.config.HikariConfiguration
import java.sql.Connection
import javax.annotation.PreDestroy
import kotlin.math.pow

@Context
class RetryableHikariDataSource(
    hikariConfiguration: HikariConfiguration,
    databaseConfiguration: DatabaseConfiguration
) : HikariDataSource() {

    private val maxRetries = 2

    @Suppress("MutationRule")
    private var retriedCount = 0

    private val log = LoggerFactory.getLogger(this::class.java.simpleName)

    init {
        leakDetectionThreshold = hikariConfiguration.leakDetectionThreshold
        maximumPoolSize = hikariConfiguration.maximumPoolSize
        driverClassName = "org.postgresql.Driver"

        jdbcUrl = databaseConfiguration.url
        username = databaseConfiguration.username
        password = databaseConfiguration.password
        driverClassName = databaseConfiguration.driverClassName
    }

    override fun getConnection(): Connection {
        return getConnectionElseRetry()
    }

    @PreDestroy
    override fun close() {
        runCatching {
            super.close()
        }
    }

    private fun getConnectionElseRetry(): Connection {
        return try {
            Database.connect(
                url = jdbcUrl,
                driver = driverClassName,
                user = username,
                password = password
            )
            super.getConnection()
        } catch (err: Exception) {
            if (areRetryAttemptsExhausted()) throw err
            log.info("Connection setup retry attempt: ${++retriedCount}")
            val sleepTime = calculateNextRetrySleepTime()
            Thread.sleep(sleepTime.toLong())
            getConnectionElseRetry()
        }
    }

    private fun calculateNextRetrySleepTime() = 2.0.pow(retriedCount) * 10

    private fun areRetryAttemptsExhausted() = retriedCount >= maxRetries
}
