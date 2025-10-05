plugins {
    kotlin("jvm") version "2.2.20"
    id("io.ktor.plugin") version "3.3.0"
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
}

group = "com.rednoir"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    // Exposed ORM
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)

    // Database connection pools and drivers
    implementation(libs.hikari)
    implementation(libs.postgres)
    implementation(libs.h2)

    // Ktor server core and related features
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.config.yaml)

    // Swagger code generation
    implementation(libs.swagger.codegen.generators)

    // Dependency injection with Koin
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)

    // Database migrations
    implementation(libs.liquibase.core)

    // Environment variables loader
    implementation(libs.dotenv.kotlin)

    // JSON serialization
    implementation(libs.kotlinx.serialization.json)

    // JWT handling
    implementation(libs.java.jwt)

    // Password hashing
    implementation(libs.jbcrypt)

    // Logging
    implementation(libs.logback.classic)

    // Testing
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.test.host)
}
