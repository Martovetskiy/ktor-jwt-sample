package presentation.mapper

import application.dto.auth.LoginRequest
import application.dto.auth.LoginResponse
import application.dto.auth.RefreshTokensRequest
import application.dto.auth.RegisterRequest
import application.dto.auth.RegisterResponse
import application.dto.auth.TokensResponse
import presentation.dto.auth.LoginRequestSerial
import presentation.dto.auth.LoginResponseSerial
import presentation.dto.auth.RefreshTokensRequestSerial
import presentation.dto.auth.RegisterRequestSerial
import presentation.dto.auth.RegisterResponseSerial
import presentation.dto.auth.TokensResponseSerial
import tech.mappie.api.ObjectMappie

object LoginRequestMapper : ObjectMappie<LoginRequestSerial, LoginRequest>()
object RegisterRequestMapper : ObjectMappie<RegisterRequestSerial, RegisterRequest>()
object RefreshTokensRequestMapper : ObjectMappie<RefreshTokensRequestSerial, RefreshTokensRequest>()

object TokensResponseMapper : ObjectMappie<TokensResponse, TokensResponseSerial>()

object LoginResponseMapper : ObjectMappie<LoginResponse, LoginResponseSerial>(){
    override fun map(from: LoginResponse): LoginResponseSerial = mapping {
        LoginResponseSerial::user fromValue(UserResponseMapper.map(from.user))
        LoginResponseSerial::tokens fromValue(TokensResponseMapper.map(from.tokens))
    }
}

object RegisterResponseMapper : ObjectMappie<RegisterResponse, RegisterResponseSerial>(){
    override fun map(from: RegisterResponse): RegisterResponseSerial = mapping {
        RegisterResponseSerial::user fromValue(UserResponseMapper.map(from.user))
    }
}