package infrastructure.repository

import domain.entity.User
import domain.repository.UserRepository
import domain.vo.Email
import infrastructure.database.entity.UserEntity
import infrastructure.database.table.UserTable
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {
    override suspend fun findByEmail(email: Email): User? = transaction {
        val result = UserEntity.find { UserTable.email eq email.value }.firstOrNull()
        result?.toUser()
    }
    override suspend fun findById(id: Int): User? = transaction {
        UserEntity.findById(id)?.toUser()
    }

    override suspend fun findAll(): List<User> = transaction {
        UserEntity.all().map { it.toUser() }
    }

    override suspend fun create(entity: User): User = transaction {
        val newUser = UserEntity.new {
            email = entity.email
            password = entity.passwordHash
        }

        newUser.toUser()
    }

    override suspend fun update(entity: User): User = transaction {
        val userEntity = UserEntity[entity.id]
        userEntity.toUser()
    }

    override suspend fun delete(id: Int) = transaction {
        UserEntity[id].delete()
    }

}