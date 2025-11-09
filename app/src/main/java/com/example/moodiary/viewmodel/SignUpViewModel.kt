package com.example.moodiary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.SignUpRepository
import com.example.moodiary.state.SignUpUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val signUpRepository: SignUpRepository
): ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email, errorMessage = null)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        uiState = uiState.copy(confirmPassword = confirmPassword, errorMessage = null)
    }

    fun signUp(onSuccess: () -> Unit) {
        val email = uiState.email
        val password = uiState.password
        val confirm = uiState.confirmPassword

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val result = signUpRepository.signUp(email, password, confirm)

            if (result.isSuccess) {
                uiState = uiState.copy(isLoading = false, success = true, errorMessage = null)
                onSuccess()
            } else {
                uiState = uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.localizedMessage)
            }
        }
    }
}