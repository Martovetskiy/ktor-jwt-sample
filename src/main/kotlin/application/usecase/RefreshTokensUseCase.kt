package application.usecase

import application.dto.auth.RefreshTokensRequest
import application.dto.auth.TokensResponse
import application.service.AuthTokenService

class RefreshTokensUseCase(
    private val authTokenService: AuthTokenService
) {
    fun execute(request: RefreshTokensRequest): TokensResponse{
        val userId = authTokenService.validateRefreshToken(request.refreshToken)
        return authTokenService.createTokens(userId = userId)
    }
}