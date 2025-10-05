package com.rednoir.application.dto.auth

import com.rednoir.application.dto.user.UserResponse

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val user: UserResponse, val tokens: TokensResponse)