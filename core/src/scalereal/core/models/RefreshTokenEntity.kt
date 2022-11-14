package scalereal.core.models

data class RefreshTokenEntity(
    val id: Long? = null,
    val userId: Long,
    val refreshToken: String,
    val revoked: Boolean
)
