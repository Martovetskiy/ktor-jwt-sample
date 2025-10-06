package presentation.mapper

import application.dto.user.UserResponse
import presentation.dto.user.UserResponseSerial
import tech.mappie.api.ObjectMappie

object UserResponseMapper : ObjectMappie<UserResponse, UserResponseSerial>()