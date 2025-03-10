package com.example.mentalhealthapp.data.repository

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

    override fun signOut() {
        authDataSource.signOut()
    }
}