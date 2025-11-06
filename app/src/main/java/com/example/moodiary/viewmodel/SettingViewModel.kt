package com.example.moodiary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _notificationsEnabled = MutableStateFlow(true)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled.asStateFlow()

    private val _darkModeEnabled = MutableStateFlow(false)
    val darkModeEnabled: StateFlow<Boolean> = _darkModeEnabled.asStateFlow()

    fun toggleNotifications() {
        _notificationsEnabled.value = !_notificationsEnabled.value
    }

    fun toggleDarkMode() {
        _darkModeEnabled.value = !_darkModeEnabled.value
        // 실제 다크모드 적용 로직은 앱 테마 제어 지점에서 처리하세요.
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            // 로그아웃 처리: 세션 삭제, 저장된 토큰 제거 등
            // TODO: 실제 로그아웃 로직을 추가
            onComplete()
        }
    }

    fun requestChangePassword() {
        // 비밀번호 변경 화면으로 네비게이션 처리
    }

    fun sendFeedback(onHandled: () -> Unit) {
        viewModelScope.launch {
            // 이메일 인텐트 또는 피드백 전송 로직을 호출
            // TODO: 실제 피드백 호출 추가
            onHandled()
        }
    }

    fun openAppInfo() {
        // 앱 정보 다이얼로그 또는 화면 표시 처리
    }
}