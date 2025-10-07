package presentation.plugins

import application.service.AuthService
import application.service.UserService
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import presentation.routes.authRoutes
import presentation.routes.userRoutes

fun Application.configureRouting() {
    val authService: AuthService by inject<AuthService>()
    val userService: UserService by inject<UserService>()
    routing {
        staticResources("/openapi","openapi")
        authRoutes(
            authService = authService
        )
        userRoutes(
            userService = userService
        )

        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
    }
}
