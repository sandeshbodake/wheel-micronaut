package scalereal.api.users

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller(value = "api/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
class UsersApi(private val usersController: UsersController) {
    @Produces(MediaType.APPLICATION_JSON)
    @Get("/{id}")
    fun get(id: Long): HttpResponse<Any> =
        usersController.get(id).getHttpResponse()
}
