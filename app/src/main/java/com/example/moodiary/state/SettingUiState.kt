
package com.example.moodiary.state

data class SettingUiState(
    val notificationsEnabled: Boolean = true,
    val darkModeEnabled: Boolean = false,
    val isShowLogoutDialog: Boolean = false
)