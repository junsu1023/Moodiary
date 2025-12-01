package com.example.moodiary.state

data class ForgotPasswordUiState(
    val email: String = "",
    val isSending: Boolean = false,
    val emailError: String? = null
)