package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface ModeRepository {
    fun getDarkMode(): Flow<Boolean>
    suspend fun setDarkMode(enabled: Boolean)
}