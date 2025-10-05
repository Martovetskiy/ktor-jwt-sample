package domain.repository

import domain.entity.User
import domain.vo.Email

interface UserRepository : BaseRepository<User, Int> {
    suspend fun findByEmail(email: Email): User?
}