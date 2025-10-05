package com.rednoir.application.service

import com.rednoir.application.dto.auth.TokensResponse

interface AuthTokenService {
    fun createTokens(userId: Int): TokensResponse
    fun validateAccessToken(token: String): Int
    fun validateRefreshToken(token: String): Int
}