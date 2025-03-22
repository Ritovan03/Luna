package com.example.mentalhealthapp.data.repository

import android.util.Log
import com.example.mentalhealthapp.data.datasource.FireBaseAuthDataSource
import com.example.mentalhealthapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthRepoImp(private val authDataSource : FireBaseAuthDataSource) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = authDataSource.getCurrentUser()

    override suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return authDataSource.signIn(email, password)
    }

    override suspend fun signUp(email: String, password: String): Result<FirebaseUser> {
        return authDataSource.signUp(email, password)
    }

    override suspend fun getGoogleIdToken(): Result<String> {
        Log.v("LOGG","Getting the id")
       return authDataSource.getGoogleIdToken()
    }

    override suspend fun firebaseSignInWithGoogle(idToken: String): Result<FirebaseUser> {
        Log.v("LOGG","Using the id")
        return authDataSource.firebaseSignInWithGoogle(idToken)
    }

    override fun signOut() {
        authDataSource.signOut()
    }
}