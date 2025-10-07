package application.mapper

import application.dto.user.UserResponse
import domain.entity.User
import tech.mappie.api.ObjectMappie

object UserMapper : ObjectMappie<User, UserResponse>()
