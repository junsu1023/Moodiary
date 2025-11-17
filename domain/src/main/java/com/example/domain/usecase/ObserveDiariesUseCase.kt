package com.example.domain.usecase

import com.example.domain.model.DiaryModel
import com.example.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveDiariesUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    operator fun invoke(): Flow<List<DiaryModel>> = diaryRepository.observeCurrentUserDiaries()
}