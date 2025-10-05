package infrastructure.di

import com.rednoir.application.PasswordProvider
import com.rednoir.application.service.AuthService
import com.rednoir.application.service.AuthTokenService
import com.rednoir.domain.repository.UserRepository
import com.rednoir.infrastructure.security.BCryptPasswordProvider
import com.rednoir.infrastructure.security.JwtAuthTokenService
import com.rednoir.infrastructure.service.AuthServiceImpl
import infrastructure.config.Config
import infrastructure.config.ConfigLoader
import infrastructure.repository.UserRepositoryImpl
import org.koin.dsl.module

val infrastructureModule = module{
    single<Config> { ConfigLoader.load() }

    single<UserRepository> { UserRepositoryImpl() }

    single<AuthService> { AuthServiceImpl(
        loginUseCase = get(),
        registerUseCase = get(),
        refreshTokensUseCase = get()
    ) }

    single<PasswordProvider> { BCryptPasswordProvider() }
    single<AuthTokenService> { JwtAuthTokenService(get<Config>().jwt) }
}