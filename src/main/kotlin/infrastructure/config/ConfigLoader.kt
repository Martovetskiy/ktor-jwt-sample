package infrastructure.config

import com.rednoir.infrastructure.config.JDBC
import com.rednoir.infrastructure.config.JwtConfig
import io.github.cdimascio.dotenv.dotenv
import kotlin.text.toBooleanStrictOrNull
import kotlin.text.toLongOrNull

object ConfigLoader {
    fun load(): Config {
        val env = dotenv {
            ignoreIfMissing = true
        }

        val development = env["DEVELOPMENT"]?.toBooleanStrictOrNull() ?: false
        val dropDB = env["DROP_DB"]?.toBooleanStrictOrNull() ?: false

        val databaseType = when (env["DATABASE_TYPE"]) {
            "JDBC" -> DatabaseType.JDBC
            "LOCAL" -> DatabaseType.LOCAL
            else -> throw Exception("Unknown DATABASE_TYPE: ${env["DATABASE_TYPE"]}")
        }

        val jdbc: JDBC? = if (databaseType == DatabaseType.JDBC) {
            JDBC(
                driver = env["JDBC_DRIVER"] ?: throw Exception("JDBC_DRIVER is missing"),
                url = env["JDBC_URL"] ?: throw Exception("JDBC_URL is missing"),
                schema = env["JDBC_SCHEMA"] ?: throw Exception("JDBC_SCHEMA is missing"),
                username = env["JDBC_USERNAME"] ?: throw Exception("JDBC_USERNAME is missing"),
                password = env["JDBC_PASSWORD"] ?: throw Exception("JDBC_PASSWORD is missing"),
            )
        } else null

        val jwt = JwtConfig(
            iss = env["JWT_ISS"] ?: throw Exception("JWT_ISS is missing"),
            sub = env["JWT_SUB"] ?: throw Exception("JWT_SUB is missing"),
            aud = env["JWT_AUD"] ?: throw Exception("JWT_AUD is missing"),
            secret = env["JWT_SECRET"] ?: throw Exception("JWT_SECRET is missing"),
            accessTokenExpirationMillis = env["JWT_ACCESS_TOKEN_EXPIRATION_MILLIS"]?.toLongOrNull()
                ?: (15 * 60 * 1000),
            refreshTokenExpirationMillis = env["JWT_REFRESH_TOKEN_EXPIRATION_MILLIS"]?.toLongOrNull()
                ?: (7 * 24 * 60 * 60 * 1000)
        )

        return Config(
            development = development,
            databaseType = databaseType,
            jdbc = jdbc,
            dropDB = dropDB,
            jwt = jwt
        )
    }
}