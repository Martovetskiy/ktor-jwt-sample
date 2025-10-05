package infrastructure.exception

import domain.exception.message

open class TokenWrongType(message: String? = null, base: String = "Wrong token Type") :
    Exception(message(base = base, message = message))