package infrastructure.security

import application.PasswordProvider
import org.mindrot.jbcrypt.BCrypt

class BCryptPasswordProvider : PasswordProvider {
    override fun hash(password: String): String =
        BCrypt.hashpw(password, BCrypt.gensalt())

    override fun equals(password: String, hash: String): Boolean {
        val result = BCrypt.checkpw(password, hash)
        return result
    }
}