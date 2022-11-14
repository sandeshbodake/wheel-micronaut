package scalereal.db.datasource.config

import io.micronaut.context.annotation.ConfigurationInject
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("datasource")
class DatabaseConfiguration @ConfigurationInject constructor(
    val url: String,
    val username: String,
    val password: String,
    val driverClassName: String
)
