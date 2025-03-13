package com.example.mentalhealthapp.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Tools : BottomNavItem("tools", "Tools", Icons.Default.Build)
    object Community : BottomNavItem("community", "Community", Icons.Default.Face)
    object Profile : BottomNavItem("profile", "Profile", Icons.Default.Person)
}
