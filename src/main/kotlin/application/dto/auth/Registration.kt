package com.rednoir.application.dto.auth

import com.rednoir.application.dto.user.UserResponse

data class RegisterRequest(val email: String, val password: String)
data class RegisterResponse(val user: UserResponse)