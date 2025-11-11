package com.example.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>

    fun logout()

    fun isLoggedIn(): Boolean

    fun getCurrentUserId(): String?
}