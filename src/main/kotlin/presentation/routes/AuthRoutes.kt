package presentation.routes

import application.dto.auth.LoginRequest
import application.dto.auth.RefreshTokensRequest
import application.dto.auth.RegisterRequest
import application.service.AuthService
import domain.exception.UserAlreadyExistException
import domain.exception.UserNotFoundException
import infrastructure.exception.TokenWrongType
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import presentation.dto.auth.LoginResponse
import presentation.dto.auth.RegisterResponse
import presentation.dto.auth.TokensResponse
import presentation.dto.user.UserResponse
import presentation.dto.auth.LoginRequest as LoginRequestPres
import presentation.dto.auth.RefreshTokensRequest as RefreshTokensRequestPres
import presentation.dto.auth.RegisterRequest as RegisterRequestPres

fun Route.authRoutes(
    authService: AuthService
) {
    route("/auth") {

        post("/login") {
            val req = call.receive<LoginRequestPres>()
            try {
                val loginRequest = LoginRequest(
                    email = req.email,
                    password = req.password
                )
                val result = authService.login(loginRequest)

                //TODO: Create AutoMapper
                val response = LoginResponse(
                    user = UserResponse(
                        result.user.email
                    ),
                    tokens = TokensResponse(
                        result.tokens.accessToken,
                        result.tokens.refreshToken,
                        result.tokens.expireAt
                    )
                )

                call.respond(HttpStatusCode.OK, response)
            } catch (e: UserNotFoundException) {
                call.respond(HttpStatusCode.NotFound, "${e.message}")
            } catch (e: Exception){
                call.respond(HttpStatusCode.InternalServerError, "${e.message}")
            }
        }

        post("/register") {
            val req = call.receive<RegisterRequestPres>()
            try {
                val registerRequest = RegisterRequest(
                    email = req.email,
                    password = req.password
                )
                val result = authService.register(registerRequest)

                val response = RegisterResponse(
                    user = UserResponse(
                        result.user.email
                    )
                )

                call.respond(HttpStatusCode.Created, response)
            } catch (e: UserAlreadyExistException) {
                call.respond(HttpStatusCode.Conflict, "${e.message}")
            } catch (e: Exception){
                call.respond(HttpStatusCode.InternalServerError, "${e.message}")
            }
        }

        post("/refresh") {
            val req = call.receive<RefreshTokensRequestPres>()
            try {
                val request = RefreshTokensRequest(
                    refreshToken = req.refreshToken
                )
                val result = authService.refresh(request)

                val response = TokensResponse(
                    result.accessToken,
                    result.refreshToken,
                    result.expireAt
                )

                call.respond(HttpStatusCode.OK, response)
            } catch (e: TokenWrongType) {
                call.respond(HttpStatusCode.Unauthorized, "${e.message}")
            }
        }
    }
}