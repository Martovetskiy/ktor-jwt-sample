package application.dto.auth

import application.dto.user.UserResponse

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val user: UserResponse, val tokens: TokensResponse)