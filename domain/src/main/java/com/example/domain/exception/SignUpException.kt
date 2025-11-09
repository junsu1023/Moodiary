package com.example.domain.exception

sealed class SignUpException: Throwable() {
    object EmptyEmailException: SignUpException()
    object EmptyPasswordException: SignUpException()
    object EmptyConfirmPasswordException: SignUpException()
    object PasswordsDoNotMatchException: SignUpException()
    object FailedSignUp: SignUpException()
}