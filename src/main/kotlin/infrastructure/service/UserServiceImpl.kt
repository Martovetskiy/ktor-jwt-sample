package infrastructure.service

import application.dto.user.UserGetMeRequest
import application.dto.user.UserResponse
import application.dto.user.UserUpdateMeRequest
import application.service.UserService
import application.usecase.user.UserGetMeUseCase
import application.usecase.user.UserUpdateMeUseCase

class UserServiceImpl(
    private val userGetMeUseCase: UserGetMeUseCase,
    private val userUpdateMeUseCase: UserUpdateMeUseCase
) : UserService {
    override suspend fun getMe(request: UserGetMeRequest): UserResponse
        = userGetMeUseCase.execute(request)

    override suspend fun updateMe(request: UserUpdateMeRequest): UserResponse
        = userUpdateMeUseCase.execute(request)
}