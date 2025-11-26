package com.example.data.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class AuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun login(email: String, password: String): Result<Unit> = try {
        auth.signInWithEmailAndPassword(email, password).await().user
        Result.success(Unit)
    } catch (e: Exception) {
        if(e is CancellationException) throw e
        Result.failure(e)
    }

    fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null

    fun getCurrentUserId(): String? = auth.currentUser?.uid

    fun changePassword(newPassword: String): Result<Unit> = try {
        val user = auth.currentUser ?: throw IllegalStateException("로그인된 회원이 아닙니다.")
        val result = user.updatePassword(newPassword)

        if(!result.isSuccessful) throw IllegalStateException("비밀번호 변경에 실패했습니다.")
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun signOut(): Result<Unit> = try {
        val user = auth.currentUser ?: throw IllegalStateException("로그인된 회원이 아닙니다.")
        user.delete()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}