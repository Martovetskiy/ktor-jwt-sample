package application.usecase.user

import application.dto.user.UserResponse
import application.dto.user.UserUpdateMeRequest
import application.usecase.BaseUseCase
import domain.entity.User
import domain.exception.UserNotFoundException
import domain.repository.UserRepository

class UserUpdateMeUseCase(
    private val userRepository: UserRepository
) : BaseUseCase<UserUpdateMeRequest, UserResponse> {
    override suspend fun execute(request: UserUpdateMeRequest): UserResponse {
        val user = userRepository.findById(request.userId) ?: throw UserNotFoundException()
        val newUser = User(
            id = user.id,
            email = user.email,
            passwordHash = user.passwordHash,
            name = request.name,
            createdAt = user.createdAt
        )
        val result = userRepository.update(newUser)
        return UserResponse(
            result.email.value,
            result.name,
            result.createdAt
        )
    }
}