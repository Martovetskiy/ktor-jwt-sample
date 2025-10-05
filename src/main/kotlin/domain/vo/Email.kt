package com.rednoir.domain.vo

import com.rednoir.domain.exception.InvalidEmailException

data class Email(override val value: String): ValueClass<String> {
    init {
        require(isValidEmail(value)) { InvalidEmailException(value) }
    }

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email)
    }
}