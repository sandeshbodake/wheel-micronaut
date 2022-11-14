package scalereal.core.user

import jakarta.inject.Singleton
import scalereal.core.exception.user.UserNotFoundException
import scalereal.core.models.domain.User

@Singleton
class UserService(private val repository: UserRepository) {
    fun get(id: Long): User =
        repository.finBy(id) ?: throw UserNotFoundException("User not found with user id $id")
}
