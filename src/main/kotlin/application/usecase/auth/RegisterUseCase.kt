package application.usecase.auth

import application.PasswordProvider
import application.dto.auth.RegisterRequest
import application.dto.auth.RegisterResponse
import application.mapper.UserMapper
import application.usecase.BaseUseCase
import domain.entity.User
import domain.exception.UserAlreadyExistException
import domain.repository.UserRepository
import domain.vo.Email
import domain.vo.PasswordHash
import java.time.Instant

class RegisterUseCase(
    private val userRepository: UserRepository,
    private val passwordProvider: PasswordProvider
): BaseUseCase<RegisterRequest, RegisterResponse> {
    override suspend fun execute(request: RegisterRequest): RegisterResponse{
        userRepository.findByEmail(Email(request.email))?.let {
            throw UserAlreadyExistException(it.email.value)
        }

        val passwordHash = passwordProvider.hash(request.password)

        val newUser = User(
            email = Email(request.email),
            passwordHash = PasswordHash(passwordHash),
            name = request.name,
            createdAt = Instant.now()
        )

        userRepository.create(newUser)
            .let {
                return RegisterResponse(
                    user = UserMapper.map(it)
                )
            }
    }
}