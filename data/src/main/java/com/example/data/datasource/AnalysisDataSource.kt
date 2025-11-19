package com.example.data.datasource

import com.example.data.request.ChatGptRequest
import com.example.data.request.Message
import com.example.data.response.AnalysisResponse
import com.example.data.service.ChatGptApiService
import kotlinx.serialization.json.Json
import java.lang.Exception
import javax.inject.Inject

class AnalysisDataSource @Inject constructor(
    private val chatGptApiService: ChatGptApiService
) {
    suspend fun getAnalysis(diaryContent: String): Result<AnalysisResponse> {
        return try {
            val request = ChatGptRequest(
                messages = listOf(
                    Message(
                        role = "system",
                        content = """
                        You are a helpful assistant. Analyze the diary entry and provide a response in JSON format.
                        The JSON should contain:
                        1. "emotionScore": A positivity score from 0 to 100.
                        2. "quote": A short, encouraging message in Korean related to the diary content. The tone should be very casual, friendly, and supportive, like a best friend speaking (use informal language/Banmal).
                        3. "musicUrl": A string formatted as "Song Title(YouTube URL)" of a song that matches the mood of the diary.
                        
                        Respond only with the JSON object, without any additional text.
                        Example format:
                        {
                          "emotionScore": 85,
                          "quote": "오늘 하루 정말 고생 많았어! 맛있는 거 먹고 푹 쉬자.",
                          "musicUrl": "Dynamite(https://www.youtube.com/watch?v=gdZLi9oWNZg)"
                        }
                        """.trimIndent()
                    ),
                    Message(
                        role = "user",
                        content = diaryContent
                    )
                )
            )

            val response = chatGptApiService.getAnalysis(request = request)

            val jsonToString = response.choices.firstOrNull()?.message?.content?.trim()
            if(jsonToString != null) {
                val analysisResponse = Json.decodeFromString<AnalysisResponse>(jsonToString)
                Result.success(analysisResponse)
            } else {
                Result.failure(Exception("API 응답이 비어있습니다."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}