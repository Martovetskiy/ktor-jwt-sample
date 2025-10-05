package application.di

import application.usecase.LoginUseCase
import application.usecase.RefreshTokensUseCase
import application.usecase.RegisterUseCase
import org.koin.dsl.module

val applicationModule = module {
    factory { LoginUseCase(
        authTokenService = get(),
        userRepository = get(),
        passwordProvider = get()
    ) }

    factory { RegisterUseCase(
        userRepository = get(),
        passwordProvider = get()
    ) }

    factory { RefreshTokensUseCase(
        authTokenService = get()
    ) }
}