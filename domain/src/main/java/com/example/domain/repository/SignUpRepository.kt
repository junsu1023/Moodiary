package com.example.domain.repository

interface SignUpRepository {
    suspend fun signUp(email: String, password: String): Result<Unit>
}