package com.example.moodiary.state

data class DetailUiState(
    val date: String = "",
    val content: String = "",
    val music: String = "",
    val quote: String = "",
    val score: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
