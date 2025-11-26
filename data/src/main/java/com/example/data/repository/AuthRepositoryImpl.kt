package com.example.data.repository

import com.example.data.datasource.AuthDataSource
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> = authDataSource.login(email, password)

    override fun logout() = authDataSource.logout()

    override fun isLoggedIn(): Boolean = authDataSource.isLoggedIn()

    override fun getCurrentUserId(): String? = authDataSource.getCurrentUserId()

    override fun changePassword(newPassword: String): Result<Unit> = authDataSource.changePassword(newPassword)

    override fun signOut(): Result<Unit> = authDataSource.signOut()
}