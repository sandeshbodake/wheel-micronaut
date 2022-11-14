package scalereal.db.user

import jakarta.inject.Singleton
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import scalereal.core.models.domain.User
import scalereal.core.user.UserRepository
import scalereal.db.tables.Users

@Singleton
class UserRepositoryImpl : UserRepository {
    override fun finBy(userName: String): User? {
        return transaction {
            Users.select { Users.userName eq userName }.firstOrNull()?.let { result ->
                User(
                    id = result[Users.id],
                    firstName = result[Users.firstName],
                    lastName = result[Users.lastName],
                    userName = result[Users.userName]
                )
            }
        }
    }

    override fun finBy(id: Long): scalereal.core.models.domain.User? {
        return transaction {
            Users.select { Users.id eq id }.firstOrNull()?.let { result ->
                User(
                    id = result[Users.id],
                    firstName = result[Users.firstName],
                    lastName = result[Users.lastName],
                    userName = result[Users.userName]
                )
            }
        }
    }
}
