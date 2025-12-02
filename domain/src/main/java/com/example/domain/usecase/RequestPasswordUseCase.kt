package com.example.domain.usecase

import com.example.domain.exception.RequestPasswordException
import com.example.domain.repository.AuthRepository
import com.example.domain.util.isNotValidEmail
import javax.inject.Inject

class RequestPasswordResetUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Result<Unit> {
        if(email.isEmpty()) return Result.failure(RequestPasswordException.EmptyEmailException())
        if(email.isNotValidEmail()) return Result.failure(RequestPasswordException.InValidEmailException())

        return try {
            authRepository.requestPasswordReset(email)
        } catch(_: Exception) {
            Result.failure(RequestPasswordException.FailedRequestPasswordReset())
        }
    }
}