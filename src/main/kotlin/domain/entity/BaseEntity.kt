package domain.entity

interface BaseEntity<T: Any> {
    val id: T
}