package com.rednoir.domain.repository

import com.rednoir.domain.entity.User
import com.rednoir.domain.vo.Email

interface UserRepository : BaseRepository<User, Int> {
    suspend fun findByEmail(email: Email): User?
}