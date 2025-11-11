package com.example.data.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> = try {
        auth.createUserWithEmailAndPassword(email, password).await().user ?: throw IllegalStateException("회원가입 실패")
        Result.success(Unit)
    } catch (e: Exception) {
        if(e is CancellationException) throw e
        Result.failure(e)
    }
}