package domain.vo

data class PasswordHash(override val value: String): ValueClass<String> {
    override fun toString(): String {
        return "PasswordHash"
    }
}