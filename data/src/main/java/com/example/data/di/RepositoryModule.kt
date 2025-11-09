package com.example.data.di

import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSignUpRepository(
        auth: FirebaseAuth
    ): SignUpRepository = SignUpRepositoryImpl(auth)
}