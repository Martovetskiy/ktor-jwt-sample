package com.rednoir.application.usecase

import com.rednoir.application.PasswordProvider
import com.rednoir.application.dto.auth.LoginRequest
import com.rednoir.application.dto.auth.LoginResponse
import com.rednoir.application.dto.user.UserResponse
import com.rednoir.application.service.AuthTokenService
import com.rednoir.domain.exception.LoginFailedException
import com.rednoir.domain.exception.UserNotFoundException
import com.rednoir.domain.repository.UserRepository
import com.rednoir.domain.vo.Email

class LoginUseCase(
    private val authTokenService: AuthTokenService,
    private val userRepository: UserRepository,
    private val passwordProvider: PasswordProvider
) {
    suspend fun execute(request: LoginRequest): LoginResponse{
        val user = userRepository.findByEmail(Email(request.email)) ?: throw UserNotFoundException(request.email)
        val isPasswordEqual = passwordProvider.equals(request.password, user.passwordHash.value)

        if (isPasswordEqual){
            val tokens = authTokenService.createTokens(user.id)
            return LoginResponse(
                user = UserResponse(
                    email = user.email.value
                ),
                tokens = tokens
            )
        }
        else{
            throw LoginFailedException()
        }
    }
}