package infrastructure.mapper

import domain.entity.User
import infrastructure.database.entity.UserEntity
import tech.mappie.api.ObjectMappie
import java.time.ZoneOffset

object UserMapper: ObjectMappie<UserEntity, User>(){
    override fun map(from: UserEntity): User = mapping {
        User::createdAt fromValue from.createdAt.toInstant(ZoneOffset.UTC)
    }
}