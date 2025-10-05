package com.rednoir.infrastructure.service

import com.rednoir.application.dto.auth.LoginRequest
import com.rednoir.application.dto.auth.LoginResponse
import com.rednoir.application.dto.auth.RefreshTokensRequest
import com.rednoir.application.dto.auth.RegisterRequest
import com.rednoir.application.dto.auth.RegisterResponse
import com.rednoir.application.dto.auth.TokensResponse
import com.rednoir.application.service.AuthService
import com.rednoir.application.usecase.LoginUseCase
import com.rednoir.application.usecase.RefreshTokensUseCase
import com.rednoir.application.usecase.RegisterUseCase

class AuthServiceImpl(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val refreshTokensUseCase: RefreshTokensUseCase
) : AuthService {
    override suspend fun login(request: LoginRequest): LoginResponse =  loginUseCase.execute(request)

    override suspend fun register(request: RegisterRequest): RegisterResponse = registerUseCase.execute(request)

    override suspend fun refresh(request: RefreshTokensRequest): TokensResponse = refreshTokensUseCase.execute(request)
}