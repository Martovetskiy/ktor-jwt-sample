package infrastructure.service

import application.dto.auth.LoginRequest
import application.dto.auth.LoginResponse
import application.dto.auth.RefreshTokensRequest
import application.dto.auth.RegisterRequest
import application.dto.auth.RegisterResponse
import application.dto.auth.TokensResponse
import application.service.AuthService
import application.usecase.auth.LoginUseCase
import application.usecase.auth.RefreshTokensUseCase
import application.usecase.auth.RegisterUseCase

class AuthServiceImpl(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val refreshTokensUseCase: RefreshTokensUseCase
) : AuthService {
    override suspend fun login(request: LoginRequest): LoginResponse =  loginUseCase.execute(request)

    override suspend fun register(request: RegisterRequest): RegisterResponse = registerUseCase.execute(request)

    override suspend fun refresh(request: RefreshTokensRequest): TokensResponse = refreshTokensUseCase.execute(request)
}