package com.example.mentalhealthapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.R

@Composable
fun HomeScreen() {
    val navController = rememberNavController() // ✅ Remember NavController for this screen

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            DailyStreakTopBar(
                date = "Sat, 23 February 2025",
                username = "Shyam",
                xp = 90,
                mood = "Happy",
                streakDays = 3,
                quoteOfTheDay = "\"I want to meet your madre, give respect to your padre\""
            )
        },

        floatingActionButton = {
            FloatingActionButton(navController)
        },// ✅ Custom Top Bar

        bottomBar = {
            BottomNavigationBar(navController)
        }, // ✅ Bottom Navigation Bar

        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.offwhite_screen_color),

    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeNavGraph(navController)
        }
    }
}

@Composable
@Preview
fun homescreenpreview() {
    HomeScreen()
}