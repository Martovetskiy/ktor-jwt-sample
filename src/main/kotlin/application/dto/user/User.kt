package application.dto.user

import java.time.Instant

data class UserGetMeRequest(val userID: Int)
data class UserUpdateMeRequest(val userId: Int, val name: String)

data class UserResponse(
    val email: String,
    val name: String? = null,
    val createdAt: Instant
)
