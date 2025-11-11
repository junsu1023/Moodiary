package com.example.domain.usecase

import com.example.domain.exception.LoginException
import com.example.domain.repository.AuthRepository
import com.example.domain.util.isNotValidEmail
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        if(email.isEmpty()) return Result.failure(LoginException.EmptyEmailException())
        if(email.isNotValidEmail()) return Result.failure(LoginException.InValidEmailException())
        if(password.isEmpty()) return Result.failure(LoginException.EmptyPasswordException())
        if(password.length < 6) return Result.failure(LoginException.InValidPasswordException())

        return try {
            authRepository.login(email, password)
        } catch(_: Exception) {
            Result.failure(LoginException.FailedLogin())
        }
    }
}