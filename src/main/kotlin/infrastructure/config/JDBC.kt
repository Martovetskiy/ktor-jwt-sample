package com.rednoir.infrastructure.config

data class JDBC(
    val driver: String,
    val url: String,
    val schema: String,
    val username: String,
    val password: String
)