package com.rednoir.application.service

import com.rednoir.application.dto.auth.LoginRequest
import com.rednoir.application.dto.auth.LoginResponse

interface AuthService {
    suspend fun login(request: LoginRequest): LoginResponse

}