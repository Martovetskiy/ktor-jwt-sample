package infrastructure.service

import application.dto.user.UserGetMeRequest
import application.dto.user.UserResponse
import application.service.UserService
import application.usecase.user.UserGetMeUseCase

class UserServiceImpl(
    private val userGetMeUseCase: UserGetMeUseCase
) : UserService {
    override suspend fun getMe(request: UserGetMeRequest): UserResponse
        = userGetMeUseCase.execute(request)
}