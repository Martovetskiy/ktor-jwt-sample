package presentation.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseSerial(
    val email: String
){}