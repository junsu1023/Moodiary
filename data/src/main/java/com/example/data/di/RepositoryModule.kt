package com.example.data.di

import com.example.data.datasource.AnalysisDataSource
import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.DiaryRemoteDataSource
import com.example.data.datasource.SignUpDataSource
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.DiaryRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DiaryRepository
import com.example.domain.repository.SignUpRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

    @Provides
    @Singleton
    fun provideDiaryRepository(
        analysisDataSource: AnalysisDataSource,
        diaryRemoteDataSource: DiaryRemoteDataSource,
        auth: FirebaseAuth
    ): DiaryRepository = DiaryRepositoryImpl(
        analysisDataSource,
        diaryRemoteDataSource,
        auth
    )
}