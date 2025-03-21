package com.example.mentalhealthapp.presentation.chatbot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ChatbotScreen(navController: NavHostController) {
    Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
        Text("Chatbot", Modifier.fillMaxSize())
    }
}