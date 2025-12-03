package com.example.moodiary.state

data class SplashUiState(
    val isDarkMode: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isReady: Boolean = false
)