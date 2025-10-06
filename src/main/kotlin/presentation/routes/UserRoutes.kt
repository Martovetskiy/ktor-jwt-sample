package presentation.routes

import application.dto.user.UserGetMeRequest
import application.service.UserService
import domain.exception.InvalidPropertyException
import infrastructure.security.JwtClaims
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import presentation.mapper.UserResponseMapper

fun Route.userRoutes(
    userService: UserService
){
    route("/user"){
        authenticate("auth-jwt") {
            get("/get_me") {
                try {
                    val principal = call.principal<JWTPrincipal>() ?: throw InvalidPropertyException("Missing JWT Principal")
                    val userId = principal.payload.getClaim(JwtClaims.USER_ID).asInt() ?: throw InvalidPropertyException("Missing UserID")

                    val request = UserGetMeRequest(
                        userID = userId
                    )

                    val result = userService.getMe(request)

                    val response = UserResponseMapper.map(
                        result
                    )

                    call.respond(HttpStatusCode.OK, response)
                }
                catch (e: InvalidPropertyException){
                    call.respond(status = HttpStatusCode.Unauthorized, message = "${e.message}")
                } catch (e: Exception){
                    call.respond(status = HttpStatusCode.InternalServerError, message = "${e.message}")
                }
            }
        }
    }
}