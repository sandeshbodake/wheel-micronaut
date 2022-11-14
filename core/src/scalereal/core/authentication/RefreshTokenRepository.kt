package scalereal.core.authentication

import scalereal.core.models.RefreshTokenEntity

interface RefreshTokenRepository {
    fun save(
        userId: Long,
        refreshToken: String,
        revoked: Boolean
    ): RefreshTokenEntity

    fun findByRefreshToken(refreshToken: String): RefreshTokenEntity?

    fun updateByUsername(
        userId: Long,
        revoked: Boolean
    ): Long
}
