package application.dto.auth

import application.dto.user.UserResponse

data class RegisterRequest(val email: String, val password: String, val name: String? = null)
data class RegisterResponse(val user: UserResponse)