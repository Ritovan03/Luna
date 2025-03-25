package com.example.mentalhealthapp.di

import com.example.mentalhealthapp.BuildConfig
import com.example.mentalhealthapp.data.datasource.FireBaseAuthDataSource
import com.example.mentalhealthapp.data.datasource.ToDoDataSource
import com.example.mentalhealthapp.data.repository.AuthRepoImp
import com.example.mentalhealthapp.data.repository.TodoRepositoryImpl
import com.example.mentalhealthapp.domain.Usecases.AddTodoUseCase
import com.example.mentalhealthapp.domain.Usecases.DeleteTodoUseCase
import com.example.mentalhealthapp.domain.Usecases.GetTodosUseCase
import com.example.mentalhealthapp.domain.Usecases.SignInUseCase
import com.example.mentalhealthapp.domain.Usecases.SignInWithGoogleUseCase
import com.example.mentalhealthapp.domain.Usecases.SignUpUseCase
import com.example.mentalhealthapp.domain.Usecases.UpdateTodoUseCase
import com.example.mentalhealthapp.domain.repository.AuthRepository
import com.example.mentalhealthapp.domain.repository.TodoRepository
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
        return FireBaseAuthDataSource(BuildConfig.WEB_CLIENT_ID)
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
        return  SignInWithGoogleUseCase( repository = repository)
    }

    @Provides
    @Singleton
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase {
        return AddTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase {
        return DeleteTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodosUseCase {
        return GetTodosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase {
        return UpdateTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToDoDataSource(
    ): ToDoDataSource {
        return ToDoDataSource()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(
        dataSource: ToDoDataSource
    ): TodoRepository {
        return TodoRepositoryImpl(dataSource)
    }

}