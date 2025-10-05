package com.rednoir.application.usecase

import com.rednoir.application.dto.auth.RefreshTokensRequest
import com.rednoir.application.dto.auth.TokensResponse
import com.rednoir.application.service.AuthTokenService

class RefreshTokensUseCase(
    private val authTokenService: AuthTokenService
) {
    fun execute(request: RefreshTokensRequest): TokensResponse{
        val userId = authTokenService.validateRefreshToken(request.refreshToken)
        return authTokenService.createTokens(userId = userId)
    }
}