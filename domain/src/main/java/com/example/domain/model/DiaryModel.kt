package com.example.domain.model

import java.util.Date

data class DiaryModel(
    val userId: String,
    val content: String,
    val timeStamp: Date? = null,
    val emotionScore: Int,
    val quote: String,
    val musicUrl: String
)
