package com.example.moodiary.state

data class ForgotPasswordUiState(
    val email: String = "",
    val isSending: Boolean = false,
    val sendState: SendState = SendState.NONE,
    val emailError: String? = null,
    val failedError: String? = null
)

enum class SendState {
    NONE,
    SUCCESS,
    FAILED
}