package presentation.routes

import application.dto.user.UserGetMeRequest
import application.dto.user.UserUpdateMeRequest
import application.service.UserService
import domain.exception.InvalidPropertyException
import infrastructure.security.JwtClaims
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import presentation.dto.user.UserUpdateMeRequestSerial
import presentation.mapper.UserResponseMapper

fun Route.userRoutes(
    userService: UserService
){
    route("/user"){
        authenticate("auth-jwt") {
            get("/getMe") {
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
                } catch (e: InvalidPropertyException){
                    call.respond(status = HttpStatusCode.Unauthorized, message = "${e.message}")
                } catch (e: BadRequestException){
                    call.respond(HttpStatusCode.BadRequest, "${e.cause!!.message}")
                } catch (e: Exception){
                    call.respond(status = HttpStatusCode.InternalServerError, message = "${e.message}")
                }
            }
            post("/updateMe") {
                try {
                    val principal = call.principal<JWTPrincipal>() ?: throw InvalidPropertyException("Missing JWT Principal")
                    val userId = principal.payload.getClaim(JwtClaims.USER_ID).asInt() ?: throw InvalidPropertyException("Missing UserID")
                    val req = call.receive<UserUpdateMeRequestSerial>()

                    val request = UserUpdateMeRequest(
                        userId = userId,
                        name = req.name
                    )

                    val result = userService.updateMe(request)

                    val response = UserResponseMapper.map(
                        result
                    )

                    call.respond(HttpStatusCode.OK, response)
                } catch (e: InvalidPropertyException){
                    call.respond(status = HttpStatusCode.Unauthorized, message = "${e.message}")
                } catch (e: BadRequestException){
                    call.respond(HttpStatusCode.BadRequest, "${e.cause!!.message}")
                } catch (e: Exception){
                    call.respond(status = HttpStatusCode.InternalServerError, message = "${e.message}")
                }
            }
        }
    }
}