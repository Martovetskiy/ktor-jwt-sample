package presentation.plugins

import application.service.AuthTokenService
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.exceptions.JWTVerificationException
import infrastructure.security.JwtAuthTokenService
import infrastructure.exception.TokenWrongType
import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.auth.jwt.jwt
import org.koin.ktor.ext.inject


//TODO: Remind this
fun Application.configureSecurity() {
    val authTokenService: AuthTokenService by inject()
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "ktor sample app"
            verifier(getJwtVerifier(authTokenService))
            validate { credential ->
                try {
                    JWTPrincipal(credential.payload)
                } catch (e: JWTVerificationException) {
                    null
                } catch (e: TokenWrongType) {
                    null
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