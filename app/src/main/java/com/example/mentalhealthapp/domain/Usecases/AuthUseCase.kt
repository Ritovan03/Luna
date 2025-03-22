package com.example.mentalhealthapp.domain.Usecases

import android.util.Log
import com.example.mentalhealthapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class SignInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
        return repository.signIn(email, password)
    }
}

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
        return repository.signUp(email, password)
    }
}

class SignInWithGoogleUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result<FirebaseUser> {
        val token = repository.getGoogleIdToken()
        Log.v("TAGY","Got token ${token}")
       return token.fold(
           onSuccess = {
               repository.firebaseSignInWithGoogle(it)
           },
           onFailure = {
               Result.failure(it)
           }
       )
    }
}