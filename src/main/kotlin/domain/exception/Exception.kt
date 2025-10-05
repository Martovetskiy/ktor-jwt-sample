package com.rednoir.domain.exception

open class InvalidPropertyException(message: String? = null, base: String = "Invalid property") :
        Exception(message(base = base, message = message))

class InvalidEmailException(message: String?, base: String = "Email invalid") :
        InvalidPropertyException(message, base)

fun message(base: String, message: String?) = base + if(message.isNullOrEmpty()) ": $message" else ""