package com.rednoir.domain.exception

//TODO: Create more custom exceptions for repository, etc

open class InvalidPropertyException(message: String? = null, base: String = "Invalid property") :
        Exception(message(base = base, message = message))

open class AlreadyExistException(message: String? = null, base: String = "Already exist") :
        Exception(message(base = base, message = message))

open class NotFoundException(message: String? = null, base: String = "Not found") :
    Exception(message(base = base, message = message))

class EmailInvalidException(message: String? = null, base: String = "Email invalid") :
        InvalidPropertyException(message, base)

class UserAlreadyExistException(message: String? = null, base: String = "User already exist") :
    AlreadyExistException(message(base = base, message = message))

class UserNotFoundException(message: String? = null, base: String = "User not found") :
    NotFoundException(message(base = base, message = message))

class LoginFailedException(message: String? = null, base: String = "Invalid authentication") :
    Exception(message(base = base, message = message))

fun message(base: String, message: String?) = base + if(message.isNullOrEmpty()) ": $message" else ""