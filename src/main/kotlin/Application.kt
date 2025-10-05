import io.ktor.server.application.*
import io.ktor.server.cio.EngineMain
import presentation.plugins.configurationCORS
import presentation.plugins.configureContentNegotiation
import presentation.plugins.configureKoin
import presentation.plugins.configureRouting
import presentation.plugins.configureSecurity

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureKoin()
    configureContentNegotiation()
    configureSecurity()
    configurationCORS()
    configureRouting()
}
