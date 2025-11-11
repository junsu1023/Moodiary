package com.example.data.datasource

import android.util.Patterns
import com.example.domain.exception.SignUpException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignUpDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Unit> {
        return suspendCancellableCoroutine { continuation ->
            if(email.isEmpty()) {
                continuation.resume(Result.failure(SignUpException.EmptyEmailException()))
                return@suspendCancellableCoroutine
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                continuation.resume(Result.failure(SignUpException.InValidEmailException()))
                return@suspendCancellableCoroutine
            }

            if(password.isEmpty()) {
                continuation.resume(Result.failure(SignUpException.EmptyPasswordException()))
                return@suspendCancellableCoroutine
            }

            if(confirmPassword.isEmpty()) {
                continuation.resume(Result.failure(SignUpException.EmptyPasswordException()))
                return@suspendCancellableCoroutine
            }

            if(password != confirmPassword) {
                continuation.resume(Result.failure(SignUpException.PasswordsDoNotMatchException()))
                return@suspendCancellableCoroutine
            }

            val task = auth.createUserWithEmailAndPassword(email, password)
            val cancelled = AtomicBoolean(false)

            val listener = OnCompleteListener<AuthResult> { task ->
                if(cancelled.get()) return@OnCompleteListener

                if(task.isSuccessful) {
                    continuation.resume(Result.success(Unit))
                } else {
                    val ex = task.exception ?: SignUpException.FailedSignUp()
                    continuation.resumeWithException(ex)
                }
            }

            task.addOnCompleteListener(listener)

            continuation.invokeOnCancellation {
                cancelled.set(true)
            }
        }
    }
}