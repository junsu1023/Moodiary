package com.example.data.response

import com.example.data.request.Message

data class ChatGptResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)