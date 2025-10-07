package application.usecase.user

import application.dto.user.UserResponse
import application.dto.user.UserUpdateMeRequest
import application.mapper.UserMapper
import application.usecase.BaseUseCase
import domain.exception.UserNotFoundException
import domain.repository.UserRepository

class UserUpdateMeUseCase(
    private val userRepository: UserRepository
) : BaseUseCase<UserUpdateMeRequest, UserResponse> {
    override suspend fun execute(request: UserUpdateMeRequest): UserResponse {
        val user = userRepository.findById(request.userId) ?: throw UserNotFoundException()
        val newUser = user.copy(
            name = request.name
        )
        val result = userRepository.update(newUser)
        return UserMapper.map(result)
    }
}