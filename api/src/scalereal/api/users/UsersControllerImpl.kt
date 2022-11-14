package scalereal.api.users

import jakarta.inject.Singleton
import scalereal.api.common.Response
import scalereal.api.common.ResponseType
import scalereal.core.exception.user.UserNotFoundException
import scalereal.core.user.UserService

@Singleton
class UsersControllerImpl(private val userService: UserService) : UsersController {
    override fun get(id: Long): Response<Any> {
        return try {
            Response(
                ResponseType.SUCCESS,
                "",
                body = userService.get(id)
            )
        } catch (e: UserNotFoundException) {
            Response(
                ResponseType.NOT_FOUND,
                e.message.toString()
            )
        }
    }
}
