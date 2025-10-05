package application.service

import application.dto.auth.TokensResponse

interface AuthTokenService {
    fun createTokens(userId: Int): TokensResponse
    fun validateAccessToken(token: String): Int
    fun validateRefreshToken(token: String): Int
}