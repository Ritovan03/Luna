package com.example.mentalhealthapp.presentation.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun SignupScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Signup", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { navController.navigate(Route.Quiz1.route) }) {
            Text("Log in")
            Log.d("Navigation", "Navigating to Login Screen") // ✅
        }
        Button(onClick = { navController.navigate(Route.ForgotPassword.route) }) {
            Text("Forogt password")

        }
    }
}