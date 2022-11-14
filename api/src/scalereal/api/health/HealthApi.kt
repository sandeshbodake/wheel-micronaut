package scalereal.api.health

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.responses.ApiResponse
import scalereal.api.health.models.HealthResponse

@Controller
class HealthApi {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/api/health", produces = [MediaType.APPLICATION_JSON])
    @ApiResponse(
        description = "Health status"
    )
    fun health(): HttpResponse<HealthResponse> =
        HttpResponse.ok(HealthResponse())
}
