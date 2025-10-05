package infrastructure.config

import infrastructure.database.DatabaseFactory

data class Config(
    val development: Boolean,
    val databaseType: DatabaseType,
    val jdbc: JDBC?,
    val dropDB: Boolean,
    val jwt: JwtConfig
){
    init {
        if (databaseType === DatabaseType.JDBC){
            if (jdbc == null) throw Exception("JDBC not configure")
            DatabaseFactory.init(
                jdbc,
                drop = dropDB,
            )
        }
    }
}
