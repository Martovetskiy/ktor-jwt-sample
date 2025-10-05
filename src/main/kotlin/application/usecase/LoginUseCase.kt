package application.usecase

import application.PasswordProvider
import application.dto.auth.LoginRequest
import application.dto.auth.LoginResponse
import application.dto.user.UserResponse
import application.service.AuthTokenService
import domain.exception.LoginFailedException
import domain.exception.UserNotFoundException
import domain.repository.UserRepository
import domain.vo.Email

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