package presentation.dto.auth

import kotlinx.serialization.Serializable
import presentation.utils.JavaInstantSerializer
import java.time.Instant

@Serializable
data class RefreshTokensRequestSerial(
    val refreshToken: String
)

@Serializable
data class TokensResponseSerial(
    val accessToken: String,
    val refreshToken: String,
    @Serializable(with = JavaInstantSerializer::class)
    val expireAt: Instant
)