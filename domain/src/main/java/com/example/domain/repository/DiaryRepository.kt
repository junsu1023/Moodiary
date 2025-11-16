package com.example.domain.repository

import com.example.domain.model.DiaryModel

interface DiaryRepository {
    suspend fun saveDiary(content: String): Result<Unit>
}