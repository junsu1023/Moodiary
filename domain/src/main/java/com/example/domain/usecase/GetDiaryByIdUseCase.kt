package com.example.domain.usecase

import com.example.domain.model.DiaryModel
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class GetDiaryByIdUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(diaryId: String): Result<DiaryModel> = diaryRepository.getDiaryById(diaryId)
}