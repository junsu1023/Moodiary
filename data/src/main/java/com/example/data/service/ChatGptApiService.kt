package com.example.data.service

import com.example.data.request.ChatGptRequest
import com.example.data.response.ChatGptResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGptApiService {
    @POST("v1/chat/completions")
    suspend fun getAnalysis(
        @Body request: ChatGptRequest
    ): ChatGptResponse
}