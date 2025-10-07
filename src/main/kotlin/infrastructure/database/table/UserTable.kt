package infrastructure.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object UserTable : IntIdTable("users") {
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 200)
    val name = varchar("name", 100).nullable()
    val createdAt = datetime("createdAt")
}