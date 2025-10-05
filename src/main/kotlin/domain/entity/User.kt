package domain.entity

import domain.vo.Email
import domain.vo.PasswordHash

data class User(
    override val id: Int = -1,
    val email: Email,
    val passwordHash: PasswordHash
): BaseEntity<Int>
