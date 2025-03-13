package com.example.mentalhealthapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen() {
    val navController = rememberNavController() // ✅ Remember NavController for this screen

    Scaffold(
        bottomBar = { BottomNavBar(navController) }, // ✅ Bottom Navigation Bar
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle FAB Action */ },
                modifier = Modifier.navigationBarsPadding() // ✅ Prevents overlap
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeNavGraph(navController)
        }
    }
}
