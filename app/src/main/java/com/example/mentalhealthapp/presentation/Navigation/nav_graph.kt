package com.example.mentalhealthapp.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.presentation.auth.AuthCard
import com.example.mentalhealthapp.presentation.auth.AuthViewModel
import com.example.mentalhealthapp.presentation.auth.LoginScreen
import com.example.mentalhealthapp.presentation.auth.SignupScreen

@Composable
fun Navigation(startDestination : String){

    val navController = rememberNavController()

    NavHost(navController = navController,startDestination = startDestination){
       composable(route = Route.AuthRoute.route){
             val authViewModel : AuthViewModel = hiltViewModel()
             LoginScreen(authViewModel)
       }
    }
}