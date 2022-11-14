package scalereal.db.authentication

import jakarta.inject.Singleton
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import scalereal.core.authentication.RefreshTokenRepository
import scalereal.core.models.RefreshTokenEntity
import scalereal.db.tables.RefreshTokens

@Singleton
class RefreshTokenRepositoryImpl : RefreshTokenRepository {
    override fun save(userId: Long, refreshToken: String, revoked: Boolean): RefreshTokenEntity =
        transaction {
            RefreshTokens.insert {
                it[RefreshTokens.userId] = userId
                it[RefreshTokens.refreshToken] = refreshToken
                it[RefreshTokens.revoked] = revoked
            }.let { refreshToken ->
                RefreshTokenEntity(
                    userId = refreshToken[RefreshTokens.userId],
                    refreshToken = refreshToken[RefreshTokens.refreshToken],
                    revoked = refreshToken[RefreshTokens.revoked]
                )
            }
        }

    override fun findByRefreshToken(refreshToken: String): RefreshTokenEntity? =
        transaction {
            RefreshTokens.select { RefreshTokens.refreshToken eq refreshToken }.firstOrNull()?.let {
                RefreshTokenEntity(
                    it[RefreshTokens.id],
                    it[RefreshTokens.userId],
                    it[RefreshTokens.refreshToken],
                    it[RefreshTokens.revoked]
                )
            }
        }

    override fun updateByUsername(userId: Long, revoked: Boolean): Long =
        transaction {
            RefreshTokens.update({ RefreshTokens.userId eq userId }) {
                it[RefreshTokens.revoked] = revoked
            }.toLong()
        }
}
