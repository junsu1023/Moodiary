package com.example.domain.usecase

import com.example.domain.repository.ModeRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val modeRepository: ModeRepository
) {
    suspend operator fun invoke(isDarkMode: Boolean) {
        modeRepository.setDarkMode(isDarkMode)
    }
}