package com.example.mentalhealthapp.presentation.home.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mentalhealthapp.presentation.chatbot.ChatbotScreen
import com.example.mentalhealthapp.presentation.community.CommunityScreen
import com.example.mentalhealthapp.presentation.haven.HavenScreen
import com.example.mentalhealthapp.presentation.home.HomeContentScreen
import com.example.mentalhealthapp.presentation.profile.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController , mainNavController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) { HomeContentScreen(navController,mainNavController) }
        composable(BottomNavItem.Tools.route) { HavenScreen(navController,mainNavController) }
        composable(BottomNavItem.Community.route) { CommunityScreen(navController) }
        composable(BottomNavItem.Profile.route) { ProfileScreen(navController) }
        composable(BottomNavItem.Chatbot.route) { ChatbotScreen(mainNavController) }
    }
}









