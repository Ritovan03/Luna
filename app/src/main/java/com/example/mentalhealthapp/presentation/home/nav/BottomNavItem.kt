package com.example.mentalhealthapp.presentation.home.nav

import com.example.mentalhealthapp.R

sealed class BottomNavItem(val route: String, val title: String, val imageRes: Int) {
    object Home : BottomNavItem("home", "Home", R.drawable.ic_home)
    object Tools : BottomNavItem("tools", "Haven", R.drawable.ic_haven)
    object Community : BottomNavItem("community", "Community", R.drawable.ic_community)
    object Profile : BottomNavItem("profile", "Profile", R.drawable.ic_profile)
    object Chatbot : BottomNavItem("chatbot", "Chatbot", R.drawable.ic_chatbot)
}