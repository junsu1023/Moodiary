package com.example.domain.usecase

import com.example.domain.exception.SignUpException
import com.example.domain.repository.SignUpRepository
import com.example.domain.util.isNotValidEmail
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(email: String, password: String, confirmPassword: String): Result<Unit> {
        if(email.isEmpty()) return Result.failure(SignUpException.EmptyEmailException())
        if(email.isNotValidEmail()) return Result.failure(SignUpException.InValidEmailException())
        if(password.isEmpty()) return Result.failure(SignUpException.EmptyPasswordException())
        if(password.length < 6) return Result.failure(SignUpException.InValidPasswordException())
        if(confirmPassword.isEmpty()) return Result.failure(SignUpException.EmptyConfirmPasswordException())
        if(password != confirmPassword) return Result.failure(SignUpException.PasswordsDoNotMatchException())

        return try {
            signUpRepository.signUp(email, password)
        } catch (_: Exception) {
            Result.failure(SignUpException.FailedSignUp())
        }
    }
}