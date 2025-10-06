package presentation.plugins

import application.service.AuthTokenService
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.exceptions.TokenExpiredException
import infrastructure.exception.TokenWrongType
import infrastructure.security.JwtAuthTokenService
import infrastructure.security.JwtClaims
import infrastructure.security.TokenType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject


//TODO: Remind this
fun Application.configureSecurity() {
    val authTokenService: AuthTokenService by inject()
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "Ktor Server"
            verifier(getJwtVerifier(authTokenService))
            validate { credential ->
                try {
                    val userId = credential.payload.getClaim(JwtClaims.USER_ID).asInt()
                    val type = credential.payload.getClaim(JwtClaims.TYPE).asString()
                    if (type == TokenType.ACCESS_TOKEN.name) {
                        JWTPrincipal(credential.payload)
                    } else {
                        throw TokenWrongType()
                    }
                } catch (_: TokenExpiredException) {
                    null
                }
            }

            challenge { _, cause ->
                when (cause) {
                    else -> {
                        call.respond(
                            HttpStatusCode.Unauthorized,
                            "Token is not valid or has expired"
                        )
                    }
                }
            }
        }
    }
}

private fun getJwtVerifier(authTokenService: AuthTokenService): JWTVerifier =
    when (authTokenService) {
        is JwtAuthTokenService -> authTokenService.verifier
        else -> throw IllegalStateException("AuthTokenService is not a JwtAuthTokenService")
    }