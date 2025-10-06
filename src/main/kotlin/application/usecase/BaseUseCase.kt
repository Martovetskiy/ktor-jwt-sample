package application.usecase

interface BaseUseCase<Request: Any, Response: Any> {
    suspend fun execute(request: Request): Response
}