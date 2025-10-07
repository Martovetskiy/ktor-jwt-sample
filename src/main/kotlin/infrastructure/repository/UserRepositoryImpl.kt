package infrastructure.repository

import domain.entity.User
import domain.repository.UserRepository
import domain.vo.Email
import infrastructure.database.entity.UserEntity
import infrastructure.database.table.UserTable
import infrastructure.mapper.UserMapper
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.ZoneOffset

class UserRepositoryImpl : UserRepository {
    override suspend fun findByEmail(email: Email): User? = transaction {
        UserEntity.find { UserTable.email eq email.value }.firstOrNull()?.let {
            UserMapper.map(it)
        }

    }
    override suspend fun findById(id: Int): User? = transaction {
        UserEntity.findById(id)?.let {
            UserMapper.map(it)
        }

    }

    override suspend fun findAll(): List<User> = transaction {
        UserEntity.all().map { UserMapper.map(it) }
    }

    override suspend fun create(entity: User): User = transaction {
        //TODO: Maybe create mapper?
        val newUser = UserEntity.new {
            email = entity.email
            password = entity.passwordHash
            name = entity.name
            createdAt = LocalDateTime.ofInstant(entity.createdAt, ZoneOffset.UTC)
        }

        UserMapper.map(newUser)
    }

    override suspend fun update(entity: User): User = transaction {
        val userEntity = UserEntity[entity.id]
        userEntity.apply {
            name = entity.name
        }
        UserMapper.map(userEntity)
    }

    override suspend fun delete(id: Int) = transaction {
        UserEntity[id].delete()
    }

}