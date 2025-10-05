package presentation.plugins

import application.service.AuthService
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import presentation.routes.authRoutes

fun Application.configureRouting() {
    val authService: AuthService by inject()
    routing {
        staticResources("/openapi","openapi")
        authRoutes(
            authService
        )
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
    }
}
