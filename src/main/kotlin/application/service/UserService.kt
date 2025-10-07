package application.service

import application.dto.user.UserGetMeRequest
import application.dto.user.UserResponse
import application.dto.user.UserUpdateMeRequest

interface UserService {
    suspend fun getMe(request: UserGetMeRequest): UserResponse
    suspend fun updateMe(request: UserUpdateMeRequest): UserResponse
}