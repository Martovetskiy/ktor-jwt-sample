package presentation.plugins

import application.di.applicationModule
import infrastructure.di.infrastructureModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin(){
    install(Koin) {
        slf4jLogger()
        modules(
            applicationModule,
            infrastructureModule
        )
    }
}