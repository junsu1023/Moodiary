package com.example.moodiary.state

import com.example.domain.model.DiaryModel

data class HomeUiState(
    val diaries: List<DiaryModel> = emptyList(),
    val todayDiaries: List<DiaryModel> = emptyList()
)
