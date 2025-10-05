package com.rednoir.application.usecase

import com.rednoir.application.PasswordProvider
import com.rednoir.application.dto.auth.RegisterRequest
import com.rednoir.application.dto.auth.RegisterResponse
import com.rednoir.application.dto.user.UserResponse
import com.rednoir.domain.entity.User
import com.rednoir.domain.exception.UserAlreadyExistException
import com.rednoir.domain.repository.UserRepository
import com.rednoir.domain.vo.Email
import com.rednoir.domain.vo.PasswordHash

class RegisterUseCase(
    private val userRepository: UserRepository,
    private val passwordProvider: PasswordProvider
) {
    suspend fun execute(request: RegisterRequest): RegisterResponse{
        userRepository.findByEmail(Email(request.email))?.let {
            UserAlreadyExistException(it.email.value)
        }

        val passwordHash = passwordProvider.hash(request.password)

        val newUser = User(
            email = Email(request.email),
            passwordHash = PasswordHash(passwordHash)
        )

        userRepository.create(newUser)
            .let {
                return RegisterResponse(user = UserResponse(
                    email = it.email.value
                ))
            }
    }
}