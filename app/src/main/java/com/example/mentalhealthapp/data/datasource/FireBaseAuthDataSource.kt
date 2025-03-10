package com.example.mentalhealthapp.data.datasource

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import kotlin.Result

class FireBaseAuthDataSource {

    private val auth = Firebase.auth

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            Log.v("TAGY","Signing into firebase")
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Log.v("TAGY","Sign in successful")
            Result.success(result.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<FirebaseUser> {
        return try {
            Log.v("TAGY","Creating account in firebase")
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch (e: Exception) {
            Log.v("TAGY","Error occured ${e.message}")
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
