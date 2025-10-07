package presentation.routes

import application.service.AuthService
import domain.exception.LoginFailedException
import domain.exception.UserAlreadyExistException
import domain.exception.UserNotFoundException
import infrastructure.exception.TokenWrongType
import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import presentation.dto.auth.LoginRequestSerial
import presentation.dto.auth.RefreshTokensRequestSerial
import presentation.dto.auth.RegisterRequestSerial
import presentation.mapper.*

fun Route.authRoutes(
    authService: AuthService
) {
    route("/auth") {
        post("/login") {
            val req = call.receive<LoginRequestSerial>()
            try {
                val result = authService.login(
                    LoginRequestMapper.map(req)
                )
                val response = LoginResponseMapper.map(result)

                call.respond(HttpStatusCode.OK, response)
            } catch (e: UserNotFoundException) {
                call.respond(HttpStatusCode.NotFound, "${e.message}")
            } catch (e: LoginFailedException){
                call.respond(HttpStatusCode.Unauthorized, "${e.message}")
            } catch (e: BadRequestException){
                call.respond(HttpStatusCode.BadRequest, "${e.cause!!.message}")
            } catch (e: Exception){
                call.respond(HttpStatusCode.InternalServerError, "${e.message}")
            }
        }

        post("/register") {
            try {
                val req = call.receive<RegisterRequestSerial>()
                val registerRequest = RegisterRequestMapper.map(req)
                val result = authService.register(registerRequest)
                val response = RegisterResponseMapper.map(result)
                call.respond(HttpStatusCode.Created, response)
            } catch (e: UserAlreadyExistException) {
                call.respond(HttpStatusCode.Conflict, "${e.message}")
            } catch (e: BadRequestException){
                call.respond(HttpStatusCode.BadRequest, "${e.cause!!.message}")
            }
            catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "${e.message}")
            }
        }

        post("/refresh") {
            val req = call.receive<RefreshTokensRequestSerial>()
            try {
                val request = RefreshTokensRequestMapper.map(req)
                val result = authService.refresh(request)

                val response = TokensResponseMapper.map(result)

                call.respond(HttpStatusCode.OK, response)
            }
            catch (e: BadRequestException){
                call.respond(HttpStatusCode.BadRequest, "${e.cause!!.message}")
            }
            catch (e: TokenWrongType) {
                call.respond(HttpStatusCode.Unauthorized, "${e.message}")
            }
        }
    }
}