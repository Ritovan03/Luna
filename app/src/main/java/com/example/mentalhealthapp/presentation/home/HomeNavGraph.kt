package com.example.mentalhealthapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mentalhealthapp.presentation.chatbot.ChatbotScreen
import com.example.mentalhealthapp.presentation.community.CommunityScreen
import com.example.mentalhealthapp.presentation.haven.HavenScreen
import com.example.mentalhealthapp.presentation.profile.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController , mainNavController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) { HomeContentScreen(navController,mainNavController) }
        composable(BottomNavItem.Tools.route) { HavenScreen(navController) }
        composable(BottomNavItem.Community.route) { CommunityScreen(navController) }
        composable(BottomNavItem.Profile.route) { ProfileScreen(navController) }
        composable(BottomNavItem.Chatbot.route) { ChatbotScreen(navController) }
    }
}









