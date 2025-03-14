package com.example.mentalhealthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.presentation.Navigation.NavGraph
// import com.example.mentalhealthapp.presentation.Navigation.Navigation
import com.example.mentalhealthapp.presentation.Navigation.Route
import com.example.mentalhealthapp.presentation.auth.AuthCard
import com.example.mentalhealthapp.presentation.auth.AuthCard
import com.example.mentalhealthapp.ui.theme.MentalHealthAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MentalHealthAppTheme {
                val navController = rememberNavController()
                val startDestination = Route.Splash.route
                NavGraph(navController = navController, startDestination = startDestination)

            }
        }
    }
}

