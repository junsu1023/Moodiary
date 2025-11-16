package com.example.data.response

import kotlinx.serialization.Serializable

@Serializable
data class AnalysisResponse(
    val emotionScore: Int,
    val quote: String,
    val musicUrl: String
)
