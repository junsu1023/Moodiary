package com.example.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.datasource.datastore.DarkModeDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private val Context.darkModeDataStore by preferencesDataStore(name = "dark_mode")

    @Provides
    @Singleton
    fun provideDarkModeDataStore(
        @ApplicationContext context: Context
    ): DarkModeDataStore {
        return DarkModeDataStore(context.darkModeDataStore)
    }
}