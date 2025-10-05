package com.rednoir.infrastructure.config

data class JwtConfig(
    val iss: String,
    val sub: String,
    val aud: String,
    val secret: String,
    val accessTokenExpirationMillis: Long = 15 * 60 * 1000,
    val refreshTokenExpirationMillis: Long = 7 * 24 * 60 * 60 * 1000
)