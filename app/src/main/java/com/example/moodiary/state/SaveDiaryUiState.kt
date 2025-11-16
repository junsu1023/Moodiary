package com.example.moodiary.state

data class WriteScreenUiState(
    val content: String = "",
    val saveState: SaveState = SaveState.IDLE,
    val errorMessage: String? = null
)

enum class SaveState {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}