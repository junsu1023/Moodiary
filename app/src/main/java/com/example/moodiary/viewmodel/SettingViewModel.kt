package com.example.moodiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ChangePasswordUseCase
import com.example.domain.usecase.LogoutUseCase
import com.example.moodiary.state.ChangePasswordState
import com.example.moodiary.state.DialogKind
import com.example.moodiary.state.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> get() = _uiState.asStateFlow()

    private val _changePasswordDialogState = MutableStateFlow(ChangePasswordState())
    val changePasswordDialogState: StateFlow<ChangePasswordState> get() = _changePasswordDialogState.asStateFlow()

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

    fun setDialogState(isShow: Boolean, kind: DialogKind? = null) {
        _uiState.update { currentState ->
            currentState.copy(
                isShowLogoutDialog = isShow,
                dialogKind = kind ?: DialogKind.NoShow
            )
        }
    }

    fun requestChangePassword() {
        val curPassword = changePasswordDialogState.value.curPassword
        val newPassword = changePasswordDialogState.value.newPassword
        val confirmNewPassword = changePasswordDialogState.value.confirmNewPassword

        viewModelScope.launch {
            val result = changePasswordUseCase(curPassword, newPassword, confirmNewPassword)

            if(result.isSuccess) {
                println("test-kjs: success")
                _changePasswordDialogState.update { currentState ->
                    currentState.copy(
                        curPassword = "",
                        newPassword = "",
                        confirmNewPassword = "",
                        isChangePasswordSuccess = true
                    )
                }
            } else {
                println("test-kjs: failed: message= ${result.exceptionOrNull()?.message}")
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }

    fun onChangeCurPassword(newValue: String) {
        _changePasswordDialogState.update { currentState ->
            currentState.copy(
                curPassword = newValue
            )
        }
    }

    fun onChangeNewPassword(newValue: String) {
        _changePasswordDialogState.update { currentState ->
            currentState.copy(
                newPassword = newValue
            )
        }
    }

    fun onChangeConfirmNewPassword(newValue: String) {
        _changePasswordDialogState.update { currentState ->
            currentState.copy(
                confirmNewPassword = newValue
            )
        }
    }
}