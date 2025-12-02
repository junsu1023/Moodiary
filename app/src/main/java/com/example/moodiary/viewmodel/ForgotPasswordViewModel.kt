package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.RequestPasswordResetUseCase
import com.example.moodiary.state.ForgotPasswordUiState
import com.example.moodiary.state.SendState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val requestPasswordResetUseCase: RequestPasswordResetUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> get() = _uiState.asStateFlow()

    fun onUpdateEmail(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = value,
                emailError = null,
                sendState = SendState.NONE
            )
        }
    }

    fun sendRequestPasswordReset() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isSending = true,
                    emailError = null,
                    failedError = null,
                    sendState = SendState.NONE
                )
            }

            val email = uiState.value.email
            val result = requestPasswordResetUseCase(email)

            result.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        sendState = SendState.SUCCESS
                    )
                }
            }.onFailure { exception ->
                _uiState.update { currentState ->
                    currentState.copy(
                        emailError = null,
                        sendState = SendState.FAILED,
                        failedError = exception.message
                    )
                }
            }

            _uiState.update { currentState ->
                currentState.copy(
                    isSending = false,
                    emailError = null,
                    failedError = null,
                    sendState = SendState.NONE
                )
            }
        }
    }
}