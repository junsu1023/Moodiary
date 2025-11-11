package com.example.domain.util

import java.util.regex.Pattern

private const val EMAIL_ADDRESS_PATTERN = "[a-zA-Z0-9+._%\\-]{1,256}" +
        "@" +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
        ")+"

private val emailPattern = Pattern.compile(EMAIL_ADDRESS_PATTERN)

fun String.isNotValidEmail(): Boolean = !emailPattern.matcher(this).matches()