package com.example.data.repository

import com.example.data.datasource.AnalysisDataSource
import com.example.data.datasource.DiaryRemoteDataSource
import com.example.data.dto.DiaryDto
import com.example.domain.repository.DiaryRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val analysisDataSource: AnalysisDataSource,
    private val diaryRemoteDataSource: DiaryRemoteDataSource,
    private val auth: FirebaseAuth
): DiaryRepository {
    override suspend fun saveDiary(content: String): Result<Unit> {
        return try {
            val uid = auth.currentUser?.uid ?: return Result.failure(Exception("로그인된 유저가 아닙니다."))
            val (emotionScore, quote, musicUrl) = analysisDataSource.analyzeDiary(content)


            val diaryDto = DiaryDto(
                userId = uid,
                content = content,
                emotionScore = emotionScore,
                quote = quote,
                musicUrl = musicUrl
            )

            diaryRemoteDataSource.saveDiary(diaryDto)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}