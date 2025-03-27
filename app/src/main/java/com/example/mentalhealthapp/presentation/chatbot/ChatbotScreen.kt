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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mentalhealthapp.presentation.Navigation.Route
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.*
import com.example.mentalhealthapp.R

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

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.birdie)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Curved top section
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                color = Color(0xFF6AB7C3),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .size(180.dp)
                            .padding(top = 24.dp)
                    )

                    Text(
                        text = "Meet Luna",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Your AI Companion",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            // Conversation History
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {
                items(conversations) { conversation ->
                    ConversationHistoryCard(conversation)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        // FAB
        FloatingActionButton(
            onClick = { mainNavController.navigate(Route.Chat.route) },
            containerColor = Color(0xFF6AB7C3),
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "New Chat", tint = Color.White)
        }
    }
}

@Composable
fun ConversationHistoryCard(conversation: Conversation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = conversation.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = conversation.preview,
                    fontSize = 14.sp,
                    color = Color(0xFF7F8C8D),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = conversation.timestamp,
                    fontSize = 12.sp,
                    color = Color(0xFFBDC3C7)
                )
            }
        }
    }
}
