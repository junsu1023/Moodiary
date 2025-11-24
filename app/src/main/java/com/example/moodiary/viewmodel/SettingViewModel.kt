package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LogoutUseCase
import com.example.moodiary.state.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> get() = _uiState.asStateFlow()

    fun toggleNotifications() {
        _uiState.update { currentState ->
            currentState.copy(
                notificationsEnabled = !uiState.value.notificationsEnabled
            )
        }
    }

    fun toggleDarkMode() {
        _uiState.update { currentState ->
            currentState.copy(
                darkModeEnabled = !uiState.value.darkModeEnabled
            )
        }
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            logoutUseCase()
            onComplete()
        }
    }

    fun setDialogState(isShow: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isShowLogoutDialog = isShow
            )
        }
    }

    fun requestChangePassword() {
        // 비밀번호 변경 화면으로 네비게이션 처리
    }
}