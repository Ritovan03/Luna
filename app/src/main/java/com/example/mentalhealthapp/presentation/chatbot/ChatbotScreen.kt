package com.example.mentalhealthapp.presentation.chatbot


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mentalhealthapp.presentation.Navigation.Route

// Data Classes
data class Conversation(
    val id: Int,
    val title: String,
    val preview: String,
    val timestamp: String
)

data class Message(
    val text: String,
    val isFromUser: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(mainNavController: NavHostController) {
    val conversations = listOf(
        Conversation(1, "Talk about emotions", "How are you feeling today?", "2 hours ago"),
        Conversation(2, "Daily check-in", "Let's review your day", "Yesterday"),
        Conversation(3, "Coping strategies", "Breathing exercise", "3 days ago")
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mainNavController.navigate(Route.Chat.route) },
                containerColor = Color(0xFF6AB7C3), // Primary color
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "New Chat",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .background(Color(0xFFF0F4F8)) // Background color
        ) {
            items(conversations) { conversation ->
                ConversationHistoryCard(conversation)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ConversationHistoryCard(conversation: Conversation) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = conversation.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF333333) // Dark text
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = conversation.preview,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF666666) // Medium dark text
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = conversation.timestamp,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF999999) // Light text
            )
        }
    }
}
