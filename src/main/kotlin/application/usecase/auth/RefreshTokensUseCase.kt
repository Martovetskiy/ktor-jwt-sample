package application.usecase.auth

import application.dto.auth.RefreshTokensRequest
import application.dto.auth.TokensResponse
import application.service.AuthTokenService
import application.usecase.BaseUseCase

class RefreshTokensUseCase(
    private val authTokenService: AuthTokenService
): BaseUseCase<RefreshTokensRequest, TokensResponse> {
    override suspend fun execute(request: RefreshTokensRequest): TokensResponse{
        val userId = authTokenService.validateRefreshToken(request.refreshToken)
        return authTokenService.createTokens(userId = userId)
    }
}