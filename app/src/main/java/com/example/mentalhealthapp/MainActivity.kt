package com.example.mentalhealthapp

// import com.example.mentalhealthapp.presentation.Navigation.Navigation
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.presentation.Navigation.NavGraph
import com.example.mentalhealthapp.presentation.Navigation.Route
import com.example.mentalhealthapp.ui.theme.MentalHealthAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MentalHealthAppTheme {
                val navController = rememberNavController()
                val startDestination = Route.Splash.route
                NavGraph(navController = navController, startDestination = startDestination)
            }
        }
    }
}

