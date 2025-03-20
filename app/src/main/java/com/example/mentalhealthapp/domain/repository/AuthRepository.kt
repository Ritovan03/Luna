package com.example.mentalhealthapp.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signIn(email: String, password: String): Result<FirebaseUser>

    suspend fun signUp(email: String, password: String): Result<FirebaseUser>

    suspend fun firebaseSignInWithGoogle(idToken: String): FirebaseUser?


    fun signOut()
}