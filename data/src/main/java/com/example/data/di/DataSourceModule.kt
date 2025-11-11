package com.example.data.di

import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.SignUpDataSource
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(
        auth: FirebaseAuth
    ): AuthDataSource = AuthDataSource(auth)

    @Provides
    @Singleton
    fun provideSignUpDataSource(
        auth: FirebaseAuth
    ): SignUpDataSource = SignUpDataSource(auth)
}