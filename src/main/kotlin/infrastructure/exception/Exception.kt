package com.rednoir.infrastructure.exception

import com.rednoir.domain.exception.message

open class TokenWrongType(message: String? = null, base: String = "Wrong token Type") :
    Exception(message(base = base, message = message))