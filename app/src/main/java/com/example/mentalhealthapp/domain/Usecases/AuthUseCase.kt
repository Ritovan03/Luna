package com.example.mentalhealthapp.domain.Usecases

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
    suspend operator fun invoke(idToken: String): FirebaseUser? {
        return repository.firebaseSignInWithGoogle(idToken)
    }
}