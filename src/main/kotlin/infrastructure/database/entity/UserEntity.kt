package infrastructure.database.entity

import domain.vo.Email
import domain.vo.PasswordHash
import infrastructure.database.table.UserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    var email by UserTable.email.transform({ it.value }, { Email(it) })
    var password by UserTable.password.transform({ it.value }, { PasswordHash(it) })
    var name by UserTable.name
    var createdAt by UserTable.createdAt

    companion object : IntEntityClass<UserEntity>(UserTable)
}