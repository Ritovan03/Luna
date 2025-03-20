package com.example.mentalhealthapp.presentation.auth


import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.mentalhealthapp.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleSignInClient(
    private val context : Context,
    private val clientId : String
) {

    private val credentialManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isSignedIn(): Boolean{
        return firebaseAuth.currentUser != null
    }

    suspend fun signIn() : Boolean{
        Log.i("TAG","Calling SignIn")
        if(isSignedIn()){
            return true
        }
        try {
            val result = buildCredentialRequest()
            return handleSignIn(result)
        }catch (e : Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            Log.i("TAGS","${e.message}")
            return false
        }
        return false
    }

    private suspend fun handleSignIn(result: androidx.credentials.GetCredentialResponse) : Boolean{
        val credential = result.credential

        if(credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential = GoogleAuthProvider.getCredential(
                    tokenCredential.idToken,null
                )
                val authResult = firebaseAuth.signInWithCredential(authCredential).await()

                return authResult.user != null
            }catch (e : GoogleIdTokenParsingException){
                Log.i("TOKEN ERROR","${e.message}")
                return false
            }
        }else{
            println("Cred isnt googleID token")
            return false
        }
    }

    private suspend fun buildCredentialRequest() : androidx.credentials.GetCredentialResponse {

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(clientId)
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                googleIdOption
            ).build()

        return credentialManager.getCredential(
            context = context,
            request = request
        )

    }

    suspend fun signOut(){
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        firebaseAuth.signOut()
    }
}