package com.example.domain.usecase

import com.example.domain.repository.ModeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(
    private val modeRepository: ModeRepository
) {
    operator fun invoke(): Flow<Boolean> = modeRepository.getDarkMode()
}