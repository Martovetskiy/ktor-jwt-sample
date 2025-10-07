package infrastructure.di

import application.PasswordProvider
import application.service.AuthService
import application.service.AuthTokenService
import application.service.UserService
import application.usecase.auth.LoginUseCase
import application.usecase.auth.RefreshTokensUseCase
import application.usecase.auth.RegisterUseCase
import application.usecase.user.UserGetMeUseCase
import application.usecase.user.UserUpdateMeUseCase
import domain.repository.UserRepository
import infrastructure.security.BCryptPasswordProvider
import infrastructure.security.JwtAuthTokenService
import infrastructure.service.AuthServiceImpl
import infrastructure.config.Config
import infrastructure.config.ConfigLoader
import infrastructure.repository.UserRepositoryImpl
import infrastructure.service.UserServiceImpl
import org.koin.dsl.module

val infrastructureModule = module{
    single<Config> { ConfigLoader.load() }

    single<UserRepository> { UserRepositoryImpl() }

    single<AuthService> { AuthServiceImpl(
        loginUseCase = get<LoginUseCase>(),
        registerUseCase = get<RegisterUseCase>(),
        refreshTokensUseCase = get<RefreshTokensUseCase>()
    ) }

    single<PasswordProvider> { BCryptPasswordProvider() }
    single<AuthTokenService> { JwtAuthTokenService(
        config = get<Config>().jwt
    ) }
    single<UserService> { UserServiceImpl(
        userGetMeUseCase = get<UserGetMeUseCase>(),
        userUpdateMeUseCase = get<UserUpdateMeUseCase>()
    ) }
}