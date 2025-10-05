package presentation.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String
)