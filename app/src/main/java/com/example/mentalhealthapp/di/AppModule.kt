package com.example.mentalhealthapp.di

import com.example.mentalhealthapp.data.datasource.FireBaseAuthDataSource
import com.example.mentalhealthapp.data.repository.AuthRepoImp
import com.example.mentalhealthapp.domain.Usecases.SignInUseCase
import com.example.mentalhealthapp.domain.Usecases.SignUpUseCase
import com.example.mentalhealthapp.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFireBaseAuthDataSource(): FireBaseAuthDataSource {
        return FireBaseAuthDataSource()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(dataSource: FireBaseAuthDataSource): AuthRepository {
        return AuthRepoImp(dataSource)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(repository: AuthRepository): SignInUseCase {
        return SignInUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(repository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }
}