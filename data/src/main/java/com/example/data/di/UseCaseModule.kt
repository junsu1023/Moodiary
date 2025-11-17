package com.example.data.di

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DiaryRepository
import com.example.domain.repository.SignUpRepository
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.LogoutUseCase
import com.example.domain.usecase.ObserveDiariesUseCase
import com.example.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase = LoginUseCase(authRepository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        authRepository: AuthRepository
    ): LogoutUseCase = LogoutUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        signUpRepository: SignUpRepository
    ): SignUpUseCase = SignUpUseCase(signUpRepository)

    @Provides
    @Singleton
    fun provideObserveDiariesUseCase(
        diaryRepository: DiaryRepository
    ): ObserveDiariesUseCase = ObserveDiariesUseCase(diaryRepository)
}