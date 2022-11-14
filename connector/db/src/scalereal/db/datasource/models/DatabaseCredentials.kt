package scalereal.db.datasource.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class DatabaseCredentials(
    val host: String,
    val port: String,
    val db: String,
    val username: String,
    val password: String
) {
    val jdbcUrl: String
        get() = "jdbc:postgresql://$host:$port/$db"
}
