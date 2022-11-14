package scalereal.db.tables

import org.jetbrains.exposed.sql.Table

object RefreshTokens : Table("refresh_tokens") {
    val id = long("id").autoIncrement()
    val userId = reference("user_id", Users.id)
    val refreshToken = varchar("refresh_token", length = 500)
    val revoked = bool("revoked")
}
