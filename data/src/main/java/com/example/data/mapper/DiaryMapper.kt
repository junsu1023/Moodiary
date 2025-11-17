package com.example.data.mapper

import com.example.data.dto.DiaryDto
import com.example.domain.model.DiaryModel

fun DiaryDto.toModel(): DiaryModel = DiaryModel(
    this.userId,
    this.content,
    this.timeStamp,
    this.emotionScore,
    this.quote,
    this.musicUrl
)