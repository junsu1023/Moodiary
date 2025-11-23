package com.example.moodiary.state

import com.example.domain.model.DiaryModel

data class HistoryUiState(
    val diaries: List<DiaryModel> = emptyList(),
    val isLoading: Boolean = false,
    val filter: String = "모두"
)
