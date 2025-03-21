package com.example.mentalhealthapp.di

import android.content.Context
import com.example.mentalhealthapp.BuildConfig
import com.example.mentalhealthapp.data.datasource.FireBaseAuthDataSource
import com.example.mentalhealthapp.data.repository.AuthRepoImp
import com.example.mentalhealthapp.domain.Usecases.SignInUseCase
import com.example.mentalhealthapp.domain.Usecases.SignInWithGoogleUseCase
import com.example.mentalhealthapp.domain.Usecases.SignUpUseCase
import com.example.mentalhealthapp.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFireBaseAuthDataSource(@ApplicationContext context: Context): FireBaseAuthDataSource {
        return FireBaseAuthDataSource(context,BuildConfig.WEB_CLIENT_ID)
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

    @Provides
    @Singleton
    fun providesGoogleSignInUseCase(repository: AuthRepository) : SignInWithGoogleUseCase{
        return  SignInWithGoogleUseCase(repository)
    }
}