package com.example.mentalhealthapp.domain.repository

import android.content.Context
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signIn(email: String, password: String): Result<FirebaseUser>

    suspend fun signUp(email: String, password: String): Result<FirebaseUser>

    suspend fun getGoogleIdToken(context: Context): Result<String>

    suspend fun firebaseSignInWithGoogle(idToken: String): Result<FirebaseUser>


    fun signOut()
}