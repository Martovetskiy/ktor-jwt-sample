package application.dto.user

data class UserGetMeRequest(val userID: Int)

data class UserResponse(
    val email: String
)