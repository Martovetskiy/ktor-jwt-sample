package presentation.dto.auth

import kotlinx.serialization.Serializable
import presentation.dto.user.UserResponseSerial

@Serializable
data class LoginRequestSerial(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseSerial(
    val user: UserResponseSerial,
    val tokens: TokensResponseSerial
)