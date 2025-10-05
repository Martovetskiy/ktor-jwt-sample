package application.service

import application.dto.auth.LoginRequest
import application.dto.auth.LoginResponse
import application.dto.auth.RefreshTokensRequest
import application.dto.auth.RegisterRequest
import application.dto.auth.RegisterResponse
import application.dto.auth.TokensResponse

interface AuthService {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): RegisterResponse
    suspend fun refresh(request: RefreshTokensRequest): TokensResponse
}