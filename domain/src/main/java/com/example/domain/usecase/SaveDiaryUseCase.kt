package com.example.domain.usecase

import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class SaveDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(content: String): Result<Unit> = diaryRepository.saveDiary(content)
}