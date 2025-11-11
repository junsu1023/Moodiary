package com.example.data.datasource

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class AuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun login(email: String, password: String) {
        suspendCancellableCoroutine { continuation ->
            val task = auth.signInWithEmailAndPassword(email, password)
            val cancelled = AtomicBoolean(false)

            val listener = OnCompleteListener<AuthResult> { result ->
                if(result.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    continuation.resumeWithException(result.exception ?: Exception("error"))
                }
            }

            task.addOnCompleteListener(listener)

            continuation.invokeOnCancellation {
                cancelled.set(true)
            }
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean = auth.currentUser != null

    fun getCurrentUserId(): String? = auth.currentUser?.uid
}