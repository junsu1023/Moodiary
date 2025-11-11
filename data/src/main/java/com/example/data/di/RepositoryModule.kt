package com.example.data.di

import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.SignUpDataSource
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.SignUpRepository
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
        signUpDataSource: SignUpDataSource
    ): SignUpRepository = SignUpRepositoryImpl(signUpDataSource)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authDataSource: AuthDataSource
    ): AuthRepository = AuthRepositoryImpl(authDataSource)
}