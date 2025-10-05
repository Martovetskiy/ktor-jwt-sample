package presentation.dto.auth

import kotlinx.serialization.Serializable
import presentation.dto.user.UserResponse

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val user: UserResponse,
    val tokens: TokensResponse
)