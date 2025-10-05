package presentation.dto.auth

import kotlinx.serialization.Serializable
import presentation.dto.user.UserResponse

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterResponse(
    val user: UserResponse
)