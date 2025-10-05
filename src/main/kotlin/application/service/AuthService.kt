package com.rednoir.application.service

import com.rednoir.application.dto.auth.LoginRequest
import com.rednoir.application.dto.auth.LoginResponse
import com.rednoir.application.dto.auth.RefreshTokensRequest
import com.rednoir.application.dto.auth.RegisterRequest
import com.rednoir.application.dto.auth.RegisterResponse
import com.rednoir.application.dto.auth.TokensResponse

interface AuthService {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): RegisterResponse
    suspend fun refresh(request: RefreshTokensRequest): TokensResponse
}