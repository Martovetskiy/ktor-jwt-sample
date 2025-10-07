package application.di

import application.PasswordProvider
import application.service.AuthTokenService
import application.usecase.auth.LoginUseCase
import application.usecase.auth.RefreshTokensUseCase
import application.usecase.auth.RegisterUseCase
import application.usecase.user.UserGetMeUseCase
import application.usecase.user.UserUpdateMeUseCase
import domain.repository.UserRepository
import org.koin.dsl.module

val applicationModule = module {
    //Auth
    factory { LoginUseCase(
        authTokenService = get<AuthTokenService>(),
        userRepository = get<UserRepository>(),
        passwordProvider = get<PasswordProvider>()
    ) }

    factory { RegisterUseCase(
        userRepository = get<UserRepository>(),
        passwordProvider = get<PasswordProvider>()
    ) }

    factory { RefreshTokensUseCase(
        authTokenService = get<AuthTokenService>()
    ) }

    //User
    factory { UserGetMeUseCase(
        userRepository = get<UserRepository>()
    ) }

    factory { UserUpdateMeUseCase(
            userRepository = get<UserRepository>()
    )}
}