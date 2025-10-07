package application.usecase.auth

import application.PasswordProvider
import application.dto.auth.LoginRequest
import application.dto.auth.LoginResponse
import application.mapper.UserMapper
import application.service.AuthTokenService
import application.usecase.BaseUseCase
import domain.exception.LoginFailedException
import domain.exception.UserNotFoundException
import domain.repository.UserRepository
import domain.vo.Email

class LoginUseCase(
    private val authTokenService: AuthTokenService,
    private val userRepository: UserRepository,
    private val passwordProvider: PasswordProvider
): BaseUseCase<LoginRequest, LoginResponse> {
    override suspend fun execute(request: LoginRequest): LoginResponse{
        val user = userRepository.findByEmail(Email(request.email)) ?: throw UserNotFoundException(request.email)
        val isPasswordEqual = passwordProvider.equals(request.password, user.passwordHash.value)
        if (isPasswordEqual){
            val tokens = authTokenService.createTokens(user.id)
            return LoginResponse(
                user = UserMapper.map(user),
                tokens = tokens
            )
        }
        else{
            throw LoginFailedException()
        }
    }
}