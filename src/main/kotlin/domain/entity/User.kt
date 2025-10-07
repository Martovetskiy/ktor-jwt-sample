package domain.entity

import domain.vo.Email
import domain.vo.PasswordHash
import java.time.Instant

data class User(
    override val id: Int = -1,
    val email: Email,
    val passwordHash: PasswordHash,
    var name: String? = null,
    val createdAt: Instant
): BaseEntity<Int>
