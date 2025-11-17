package com.example.domain.repository

import com.example.domain.model.DiaryModel
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    suspend fun saveDiary(content: String): Result<Unit>

    fun observeCurrentUserDiaries(): Flow<List<DiaryModel>>
}