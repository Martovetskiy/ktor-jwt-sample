package presentation.dto.auth

import kotlinx.serialization.Serializable
import presentation.dto.user.UserResponseSerial

@Serializable
data class RegisterRequestSerial(
    val email: String,
    val password: String
)

@Serializable
data class RegisterResponseSerial(
    val user: UserResponseSerial
)