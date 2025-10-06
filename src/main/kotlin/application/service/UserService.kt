package application.service

import application.dto.user.UserGetMeRequest
import application.dto.user.UserResponse

interface UserService {
    suspend fun getMe(request: UserGetMeRequest): UserResponse
}