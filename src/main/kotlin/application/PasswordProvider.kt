package application

interface PasswordProvider {
    fun hash(password: String): String
    fun equals(password: String, hash: String): Boolean
}