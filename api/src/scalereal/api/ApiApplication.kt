package scalereal.api

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme

@SecurityScheme(
    name = "BearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
@OpenAPIDefinition(
    info = Info(
        title = "Sample Service",
        version = "0.1",
        description = "API specifications"
    )
)
object ApiApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("scalereal.api")
            .mainClass(ApiApplication.javaClass)
            .start()
    }
}
