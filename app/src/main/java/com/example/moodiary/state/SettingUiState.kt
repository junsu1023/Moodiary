package com.example.moodiary.state

data class SettingUiState(
    val notificationsEnabled: Boolean = true,
    val isDarkMode: Boolean = false,
    val isShowLogoutDialog: Boolean = false,
    val dialogKind: DialogKind = DialogKind.NoShow,
    val errorMessage: String? = null,
    val goLoginScreen: Boolean = false
)

data class ChangePasswordState(
    val curPassword: String = "",
    val newPassword: String = "",
    val confirmNewPassword: String = ""
)

sealed class DialogKind {
    object NoShow: DialogKind()
    object Logout: DialogKind()
    object ChangePassword: DialogKind()
}