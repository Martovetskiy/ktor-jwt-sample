package presentation.dto.user

import kotlinx.serialization.Serializable
import presentation.utils.JavaInstantSerializer
import java.time.Instant

@Serializable
data class UserUpdateMeRequestSerial(
    val name: String
)

@Serializable
data class UserResponseSerial(
    val email: String,
    val name: String? = null,
    @Serializable(with = JavaInstantSerializer::class)
    val createdAt: Instant
)