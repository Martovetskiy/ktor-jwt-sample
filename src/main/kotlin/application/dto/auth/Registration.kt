package application.dto.auth

import application.dto.user.UserResponse

data class RegisterRequest(val email: String, val password: String)
data class RegisterResponse(val user: UserResponse)