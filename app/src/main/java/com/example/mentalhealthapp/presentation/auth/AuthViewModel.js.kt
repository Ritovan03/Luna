package com.example.mentalhealthapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealthapp.domain.Usecases.SignInUseCase
import com.example.mentalhealthapp.domain.Usecases.SignInWithGoogleUseCase
import com.example.mentalhealthapp.domain.Usecases.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject
import kotlin.Result

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState

    private val _uiState = MutableStateFlow(FormState())
    val uiState : StateFlow<FormState> get() = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        val result = signInUseCase(email, password)
        _authState.value = when {
            result.isSuccess -> AuthState.Success(result.getOrNull()!!)
            result.isFailure -> AuthState.Error(result.exceptionOrNull()!!)
            else -> AuthState.Initial
        }
    }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        _authState.value = AuthState.Loading
        val result = signUpUseCase(email, password)
        _authState.value = when {
            result.isSuccess -> AuthState.Success(result.getOrNull()!!)
            result.isFailure -> AuthState.Error(result.exceptionOrNull()!!)
            else -> AuthState.Initial
        }
    }


    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            val user = signInWithGoogleUseCase.invoke(idToken)
            if (user != null) {
                AuthState.Success(user)
            } else {
                AuthState.Error(exception = Exception("Google-Sign-In failed"))
            }
        }
    }


}