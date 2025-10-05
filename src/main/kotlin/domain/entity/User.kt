package com.rednoir.domain.entity

import com.rednoir.domain.vo.Email
import com.rednoir.domain.vo.PasswordHash

data class User(
    override val id: Int = -1,
    val email: Email,
    val passwordHash: PasswordHash
): BaseEntity<Int>
