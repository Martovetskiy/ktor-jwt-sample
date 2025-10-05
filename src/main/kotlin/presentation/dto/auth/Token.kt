package presentation.dto.auth

import kotlinx.serialization.Serializable
import presentation.utils.JavaInstantSerializer
import java.time.Instant

@Serializable
data class RefreshTokensRequest(
    val refreshToken: String
)

@Serializable
data class TokensResponse(
    val accessToken: String,
    val refreshToken: String,
    @Serializable(with = JavaInstantSerializer::class)
    val expireAt: Instant
)