package com.example.data.repository

import com.example.domain.exception.SignUpException
import com.example.domain.repository.SignUpRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class SignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): SignUpRepository {
    override suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Unit> {
        return suspendCancellableCoroutine { continuation ->
            if(email.isEmpty()) {
                continuation.resumeWithException(SignUpException.EmptyEmailException)
                return@suspendCancellableCoroutine
            }

            if(password.isEmpty()) {
                continuation.resumeWithException(SignUpException.EmptyPasswordException)
                return@suspendCancellableCoroutine
            }

            if(password != confirmPassword) {
                continuation.resumeWithException(SignUpException.PasswordsDoNotMatchException)
                return@suspendCancellableCoroutine
            }

            val task = auth.createUserWithEmailAndPassword(email, password)
            val cancelled = AtomicBoolean(false)

            val listener = OnCompleteListener<AuthResult> { task ->
                if(cancelled.get()) return@OnCompleteListener

                if(task.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    val ex = task.exception ?: SignUpException.FailedSignUp
                    continuation.resumeWithException(ex)
                }
            }

            task.addOnCompleteListener(listener)

            continuation.invokeOnCancellation {
                cancelled.set(true)
            }
        }.let { Result.success(Unit) }
    }
}