package com.rednoir.infrastructure.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("users") {
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 200)
}