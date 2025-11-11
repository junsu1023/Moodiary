package com.example.moodiary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUseCase
import com.example.moodiary.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email, errorMessage = null)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password, errorMessage = null)
    }

    fun login() {
        val email  = uiState.email
        val password = uiState.password

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val result = loginUseCase(email, password)

            if(result.isSuccess) {
                uiState = uiState.copy(isLoading = false, isLoggedIn = true, errorMessage = null)
            } else {
                uiState = uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }
}