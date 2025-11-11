package com.example.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)

    fun logout()

    fun isLoggedIn(): Boolean

    fun getCurrentUserId(): String?
}