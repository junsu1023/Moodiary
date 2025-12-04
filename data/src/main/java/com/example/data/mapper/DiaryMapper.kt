package com.example.data.mapper

import com.example.data.dto.DiaryDto
import com.example.domain.model.DiaryModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun DiaryDto.toModel(): DiaryModel = DiaryModel(
    this.diaryId,
    this.userId,
    this.content,
    this.timeStamp,
    this.emotionScore,
    this.quote,
    this.musicUrl
)

fun Date.convertString(): String {
    val df = SimpleDateFormat("yyyy-MM-dd E", Locale.KOREAN)
    return df.format(this)
}