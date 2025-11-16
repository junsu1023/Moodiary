package com.example.data.datasource

import javax.inject.Inject

class AnalysisDataSource @Inject constructor() {
    suspend fun analyzeDiary(content: String): Triple<Int, String, String> {
        val emotionScore = (content.length % 10) + 1
        val quote = "수고"
        val musicUrl = "https://www.youtube.com/watch?v=OxVy7wAK0Jw"

        return Triple(emotionScore, quote, musicUrl)
    }
}