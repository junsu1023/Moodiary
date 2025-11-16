package com.example.data.request

import com.google.gson.annotations.SerializedName

data class ChatGptRequest(
    @SerializedName("model") val model: String = "gpt-3.5-turbo",
    @SerializedName("messages") val messages: List<Message>
)

data class Message(
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String
)
