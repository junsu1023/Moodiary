
package com.example.domain.usecase

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Result<Unit> = authRepository.signOut()
}