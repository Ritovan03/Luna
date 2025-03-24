package com.example.mentalhealthapp.data.datasource

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException


class FireBaseAuthDataSource(private val webClientId: String){


    private val auth = Firebase.auth

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Log.v("TAGY","Sign in successful")
            Result.success(result.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user!!)
        } catch (e: Exception) {
            Log.v("TAGY","Error occurred ${e.message}")
            Result.failure(e)
        }
    }


    suspend fun getGoogleIdToken(context: Context) : Result<String> {
        return try {
            val credentialResponse = buildCredentialRequest(context)
            val credential = credentialResponse.credential

            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                Result.success(tokenCredential.idToken)
            } else {
                Result.failure(Exception("Credential is not a Google ID token"))
            }

        }catch (e : Exception) {
            if (e is CancellationException) throw e
            Result.failure(e)
        }
    }

    private suspend fun buildCredentialRequest(context: Context) : androidx.credentials.GetCredentialResponse {
         val credentialManager = CredentialManager.create(context)

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .setAutoSelectEnabled(false)
            .build()

        val request = androidx.credentials.GetCredentialRequest.Builder()
            .addCredentialOption(
                googleIdOption
            ).build()

        return credentialManager.getCredential(
            context = context,
            request = request
        )

    }



    suspend fun firebaseSignInWithGoogle(idToken: String): Result<FirebaseUser> {
        return try {
            Log.v("TAGYYY","Creating account in firebase")
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(firebaseCredential).await()
            Log.v("TAGYYY","Done with google Sign in")
            Result.success(result.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    fun signOut() {
        auth.signOut()
    }

}