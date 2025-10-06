package application.di

import application.usecase.auth.LoginUseCase
import application.usecase.auth.RefreshTokensUseCase
import application.usecase.auth.RegisterUseCase
import application.usecase.user.UserGetMeUseCase
import org.koin.dsl.module

val applicationModule = module {
    //Auth
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

    //User
    factory { UserGetMeUseCase(
        userRepository = get()
    ) }
}