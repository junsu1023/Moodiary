package com.example.domain.usecase

import com.example.domain.exception.ChangePasswordException
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(password: String, newPassword: String, confirmNewPassword: String): Result<Unit> {
        if(password.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) return Result.failure(ChangePasswordException.EmptyAreaException())
        if(newPassword.length < 6) return Result.failure(ChangePasswordException.InValidPasswordException())
        if(newPassword != confirmNewPassword) return Result.failure(ChangePasswordException.PasswordsDoNotMatchException())

        return try {
            authRepository.changePassword(newPassword)
            Result.success(Unit)
        } catch(_: Exception) {
            Result.failure(ChangePasswordException.FailedChangePassword())
        }
    }
}