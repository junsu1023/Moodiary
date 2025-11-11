package com.example.data.di

import com.example.data.datasource.AuthDataSource
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.repository.AuthRepository
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

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataSource: AuthDataSource
    ): AuthRepository = AuthRepositoryImpl(authDataSource)
}