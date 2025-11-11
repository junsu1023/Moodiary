package com.example.data.repository

import com.example.data.datasource.SignUpDataSource
import com.example.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpDataSource
): SignUpRepository {
    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Unit> {
        return signUpDataSource.signUp(email, password, confirmPassword)
    }
}