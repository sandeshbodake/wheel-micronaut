package scalereal.api.users

import scalereal.api.common.Response

interface UsersController {
    fun get(id: Long): Response<Any>
}
