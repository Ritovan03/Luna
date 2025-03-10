package com.example.mentalhealthapp.presentation.auth

import com.google.firebase.auth.FirebaseUser

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val exception: Throwable) : AuthState()
}