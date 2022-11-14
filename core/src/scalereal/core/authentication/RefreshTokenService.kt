package scalereal.core.authentication

import jakarta.inject.Inject
import jakarta.inject.Singleton
import scalereal.core.exception.user.UserNotFoundException
import scalereal.core.models.RefreshTokenEntity
import scalereal.core.models.domain.User
import scalereal.core.user.UserRepository

@Singleton
class RefreshTokenService(
    @Inject private val refreshTokenRepository: RefreshTokenRepository,
    @Inject private val userRepository: UserRepository
) {
    fun save(userName: String, token: String, revoked: Boolean): RefreshTokenEntity =
        refreshTokenRepository.save(getUser(userName).id, token, revoked)

    fun findByRefreshToken(refreshToken: String): Pair<User, RefreshTokenEntity?> {
        val token = refreshTokenRepository.findByRefreshToken(refreshToken)
        val user = userRepository.finBy(token!!.userId)!!
        return Pair(user, token)
    }

    fun updateByUsername(userName: String, revoked: Boolean): Long {
        return refreshTokenRepository.updateByUsername(getUser(userName).id, revoked)
    }

    private fun getUser(userName: String): User =
        userRepository.finBy(userName) ?: throw UserNotFoundException("User not found by $userName")
}
