package com.example.data.repository

import com.example.data.datasource.AnalysisDataSource
import com.example.data.datasource.DiaryRemoteDataSource
import com.example.data.dto.DiaryDto
import com.example.data.mapper.toModel
import com.example.domain.model.DiaryModel
import com.example.domain.repository.DiaryRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val analysisDataSource: AnalysisDataSource,
    private val diaryRemoteDataSource: DiaryRemoteDataSource,
    private val auth: FirebaseAuth
): DiaryRepository {
    override suspend fun saveDiary(content: String): Result<Unit> {
        return try {
            val uid = auth.currentUser?.uid ?: return Result.failure(Exception("로그인된 유저가 아닙니다."))
            val analysisResponse = analysisDataSource.getAnalysis(content).getOrThrow()

            val diaryDto = DiaryDto(
                userId = uid,
                content = content,
                emotionScore = analysisResponse.emotionScore,
                quote = analysisResponse.quote,
                musicUrl = analysisResponse.musicUrl
            )

            diaryRemoteDataSource.saveDiary(diaryDto)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun observeCurrentUserDiaries(): Flow<List<DiaryModel>> {
        val uid = auth.currentUser?.uid ?: throw IllegalStateException("로그인된 유저가 아닙니다.")
        return diaryRemoteDataSource.observeUserDiaries(uid).map { it.map { dto -> dto.toModel() } }
    }
}