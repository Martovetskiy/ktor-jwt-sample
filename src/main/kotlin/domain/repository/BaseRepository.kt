package com.rednoir.domain.repository

interface BaseRepository<Entity: Any, ID: Any> {
    suspend fun findById(id: ID): Entity?

    //TODO: Create `findAll()` with pagination
    suspend fun findAll(): List<Entity>
    suspend fun create(entity: Entity): Entity
    suspend fun update(entity: Entity): Entity
    suspend fun delete(id: ID)
}