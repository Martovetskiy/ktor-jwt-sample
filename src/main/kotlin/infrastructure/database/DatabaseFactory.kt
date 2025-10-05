package infrastructure.database

import infrastructure.config.JDBC
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.jetbrains.exposed.sql.Database
import liquibase.database.DatabaseFactory as LiquibaseDatabaseFactory

object DatabaseFactory {
    fun init(
        jdbc: JDBC,
        drop: Boolean = false,
    ) {
        val config = HikariConfig().apply {
            driverClassName = jdbc.driver
            jdbcUrl = "${jdbc.url}?currentSchema=${jdbc.schema}"
            username = jdbc.username
            password = jdbc.password
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        val ds = HikariDataSource(config)

        Database.connect(ds)


        ds.connection.use { conn ->
            val database = LiquibaseDatabaseFactory
                .getInstance()
                .findCorrectDatabaseImplementation(JdbcConnection(conn))
            val liquibase = Liquibase(
                "db/changelog/db.changelog-master.yaml",
                ClassLoaderResourceAccessor(),
                database
            )
            if (drop) {
                liquibase.dropAll()
            }

            liquibase.update()
        }

    }
}