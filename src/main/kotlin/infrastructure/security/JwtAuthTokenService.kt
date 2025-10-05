package com.rednoir.infrastructure.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.rednoir.application.dto.auth.TokensResponse
import com.rednoir.application.service.AuthTokenService
import com.rednoir.infrastructure.config.JwtConfig
import com.rednoir.infrastructure.exception.TokenWrongType
import java.time.Instant

class JwtAuthTokenService(
    private val config: JwtConfig
) : AuthTokenService {
    private val algorithm: Algorithm = Algorithm.HMAC256(config.secret)
    private val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(config.iss)
        .withAudience(config.aud)
        .build()

    override fun createTokens(userId: Int): TokensResponse {
        val now = Instant.now()

        val accessToken = JWT.create()
            .withIssuer(config.iss)
            .withAudience(config.aud)
            .withSubject(userId.toString())
            .withClaim(JwtClaims.USER_ID, userId)
            .withClaim(JwtClaims.TYPE, TokenType.ACCESS_TOKEN.name)
            .withIssuedAt(now)
            .withExpiresAt(now.plusMillis(config.accessTokenExpirationMillis))
            .sign(algorithm)

        val refreshToken = JWT.create()
            .withIssuer(config.iss)
            .withAudience(config.aud)
            .withSubject(userId.toString())
            .withClaim(JwtClaims.USER_ID, userId)
            .withClaim(JwtClaims.TYPE, TokenType.REFRESH_TOKEN.name)
            .withIssuedAt(now)
            .withExpiresAt(now.plusMillis(config.refreshTokenExpirationMillis))
            .sign(algorithm)

        return TokensResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expireAt = now.plusMillis(config.refreshTokenExpirationMillis)
        )
    }

    override fun validateAccessToken(token: String): Int {
        val decoded = verifier.verify(token)
        if (decoded.claims[JwtClaims.TYPE].toString() != TokenType.ACCESS_TOKEN.name) throw TokenWrongType("Is not ${TokenType.ACCESS_TOKEN.name}")
        return decoded.subject.toInt()
    }

    override fun validateRefreshToken(token: String): Int {
        //TODO: Create revoke mechanism
        val decoded = verifier.verify(token)
        if (decoded.claims[JwtClaims.TYPE].toString() != TokenType.REFRESH_TOKEN.name) throw TokenWrongType("Is not ${TokenType.REFRESH_TOKEN.name}")
        return decoded.subject.toInt()
    }
}

object JwtClaims {
    const val USER_ID = "user_id"
    const val TYPE = "type"
}

enum class TokenType {
    REFRESH_TOKEN,
    ACCESS_TOKEN
}