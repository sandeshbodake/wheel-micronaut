package scalereal.db.datasource.config

import io.micronaut.context.annotation.ConfigurationInject
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("hikari-configuration")
class HikariConfiguration @ConfigurationInject constructor(val maximumPoolSize: Int, val leakDetectionThreshold: Long)
