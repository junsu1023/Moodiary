package com.example.data.entity

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class DiaryEntity(
    val userId: String = "",
    val content: String = "",
    val emotionScore: Int = 0,
    val quote: String = "",
    val musicUrl: String = "",
    @ServerTimestamp
    val timeStamp: Date? = null
)
