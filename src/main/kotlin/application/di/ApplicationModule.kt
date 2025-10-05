package com.rednoir.application.di

import com.rednoir.application.usecase.LoginUseCase
import com.rednoir.application.usecase.RefreshTokensUseCase
import com.rednoir.application.usecase.RegisterUseCase
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