package com.example.data.dto

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class DiaryDto(
    val diaryId: String = "",
    val userId: String = "",
    val content: String = "",
    @ServerTimestamp
    val timeStamp: Date? = null,
    val emotionScore: Int = 0,
    val quote: String = "",
    val musicUrl: String = ""
)