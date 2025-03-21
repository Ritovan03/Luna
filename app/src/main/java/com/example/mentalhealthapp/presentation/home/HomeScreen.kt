package com.example.mentalhealthapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.top_bar.ChatbotTopBar
import com.example.mentalhealthapp.presentation.top_bar.CommunityTopBar
import com.example.mentalhealthapp.presentation.top_bar.HomeTopBar
import com.example.mentalhealthapp.presentation.top_bar.ProfileTopBar
import com.example.mentalhealthapp.presentation.top_bar.ToolsTopBar


@Composable
fun HomeScreen() {
    val navController = rememberNavController() // ✅ Remember NavController for this screen

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            when (currentRoute) {
                BottomNavItem.Home.route -> HomeTopBar()
                BottomNavItem.Tools.route -> ToolsTopBar()
                BottomNavItem.Community.route -> CommunityTopBar()
                BottomNavItem.Profile.route -> ProfileTopBar()
                BottomNavItem.Chatbot.route -> ChatbotTopBar()
                else -> HomeTopBar() // Default case
            }
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