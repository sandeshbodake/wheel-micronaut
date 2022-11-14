package scalereal.core.user

import scalereal.core.models.domain.User

interface UserRepository {
    fun finBy(userName: String): User?
    fun finBy(id: Long): User?
}
