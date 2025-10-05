package application.dto.auth

import java.time.Instant

data class RefreshTokensRequest(val refreshToken: String)
data class TokensResponse(val accessToken: String, val refreshToken: String, val expireAt: Instant)