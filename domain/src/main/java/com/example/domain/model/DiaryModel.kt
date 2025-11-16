package com.example.domain.model

data class DiaryModel(
    val userId: String,
    val content: String,
    val emotionScore: Int,
    val quote: String,
    val musicUrl: String
)
