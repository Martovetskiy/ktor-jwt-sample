package application.usecase.user

import application.dto.user.UserGetMeRequest
import application.dto.user.UserResponse
import application.mapper.UserMapper
import application.usecase.BaseUseCase
import domain.exception.UserNotFoundException
import domain.repository.UserRepository

class UserGetMeUseCase(
    private val userRepository: UserRepository,
): BaseUseCase<UserGetMeRequest, UserResponse> {
    override suspend fun execute(request: UserGetMeRequest): UserResponse {
        val user = userRepository.findById(request.userID) ?: throw UserNotFoundException()
        return UserMapper.map(user)
    }
}