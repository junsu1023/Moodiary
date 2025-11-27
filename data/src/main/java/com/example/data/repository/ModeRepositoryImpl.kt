package com.example.data.repository

import com.example.data.datasource.datastore.DarkModeDataStore
import com.example.domain.repository.ModeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModeRepositoryImpl @Inject constructor(
    private val preferencesManager: DarkModeDataStore
) : ModeRepository {
    override fun getDarkMode(): Flow<Boolean> = preferencesManager.darkModeFlow

    override suspend fun setDarkMode(enabled: Boolean) = preferencesManager.setDarkMode(enabled)
}