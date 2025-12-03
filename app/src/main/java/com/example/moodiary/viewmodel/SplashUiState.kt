package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetDarkModeUseCase
import com.example.moodiary.state.SplashUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val auth: FirebaseAuth
): ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> get() = _uiState.asStateFlow()

    init {
        getDarkModeState()
        checkLoginStatus()

        setReady()
    }

    private fun getDarkModeState() {
        viewModelScope.launch {
            getDarkModeUseCase().collectLatest {
                _uiState.update { state ->
                    state.copy(
                        isDarkMode = it
                    )
                }
            }
        }
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoggedIn = auth.currentUser != null
                )
            }
        }
    }

    private fun setReady() {
        _uiState.update { state ->
            state.copy(
                isReady = true
            )
        }
    }
}