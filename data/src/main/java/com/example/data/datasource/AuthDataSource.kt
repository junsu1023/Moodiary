package com.example.data.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class AuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun login(email: String, password: String) {
        suspendCancellableCoroutine { continuation ->
            val task = auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { continuation.resumeWith(Result.success(Unit)) }
                .addOnFailureListener { continuation.resumeWithException(it) }

            continuation.invokeOnCancellation {

            }
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null

    fun getCurrentUserId(): String? = auth.currentUser?.uid
}