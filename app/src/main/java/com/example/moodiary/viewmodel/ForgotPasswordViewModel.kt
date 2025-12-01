package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moodiary.state.ForgotPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> get() = _uiState.asStateFlow()

    fun onUpdateEmail(value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = value,
                emailError = null
            )
        }
    }

    fun onUpdateError(error: String? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                emailError = error
            )
        }
    }
}