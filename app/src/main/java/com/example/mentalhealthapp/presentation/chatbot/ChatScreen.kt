package com.example.mentalhealthapp.presentation.chatbot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class BotResponse(
    val message: String,
    val delayMillis: Long = 1000
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(mainNavController: NavHostController) {
    var messageText by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }
    val messages = remember { mutableStateListOf<Message>() }
    val coroutineScope = rememberCoroutineScope()

    // Predefined bot responses for different scenarios
    val botResponses = remember {
        mapOf(
            "anxiety" to listOf(
                BotResponse("I understand you're feeling anxious. Let's talk about it.", 1000),
                BotResponse("Can you tell me what triggered these feelings?", 2000),
                BotResponse("Remember to take deep breaths. Would you like to try a breathing exercise?", 1500)
            ),
            "sad" to listOf(
                BotResponse("I hear that you're feeling sad. That's completely valid.", 1000),
                BotResponse("Would you like to talk about what's making you feel this way?", 2000),
                BotResponse("Sometimes sharing our feelings can help lighten the burden.", 1500)
            ),
            "stress" to listOf(
                BotResponse("Dealing with stress can be overwhelming.", 1000),
                BotResponse("Let's break down what's causing your stress.", 1500),
                BotResponse("Would you like to try a quick mindfulness exercise?", 2000)
            ),
            "default" to listOf(
                BotResponse("I'm here to listen and support you.", 1000),
                BotResponse("How can I help you feel better today?", 1500),
                BotResponse("Would you like to explore some coping strategies together?", 2000)
            )
        )
    }

    fun sendBotResponse(userMessage: String) {
        coroutineScope.launch {
            isTyping = true
            delay(1000) // Initial typing delay

            val responses = when {
                userMessage.contains("anxiety", ignoreCase = true) -> botResponses["anxiety"]
                userMessage.contains("sad", ignoreCase = true) -> botResponses["sad"]
                userMessage.contains("stress", ignoreCase = true) -> botResponses["stress"]
                else -> botResponses["default"]
            }

            responses?.forEach { response ->
                delay(response.delayMillis)
                messages.add(Message(response.message, false))
                delay(500) // Brief pause between multiple responses
            }
            isTyping = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Luna", color = Color.White)
                        if (isTyping) {
                            Text(
                                "typing...",
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { mainNavController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6AB7C3)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F8FA))
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                reverseLayout = true
            ) {
                items(messages.reversed()) { message ->
                    ChatBubble(message)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color(0xFF6AB7C3).copy(alpha = 0.1f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Default.Mic,
                            contentDescription = "Record",
                            tint = Color(0xFF6AB7C3)
                        )
                    }

                    TextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp),
                        placeholder = { Text("Share your thoughts...", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        enabled = !isTyping
                    )

                    IconButton(
                        onClick = {
                            if (messageText.isNotBlank() && !isTyping) {
                                val userMessage = messageText
                                messages.add(Message(userMessage, true))
                                messageText = ""
                                sendBotResponse(userMessage)
                            }
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color(0xFF6AB7C3),
                                CircleShape
                            ),
                        enabled = !isTyping
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(
                start = if (message.isFromUser) 60.dp else 0.dp,
                end = if (message.isFromUser) 0.dp else 60.dp
            ),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
        ) {
            if (!message.isFromUser) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_chatbot),
                        contentDescription = "Luna",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Surface(
                color = if (message.isFromUser)
                    Color(0xFF6AB7C3)
                else
                    Color.White,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = if (message.isFromUser) 20.dp else 4.dp,
                    bottomEnd = if (message.isFromUser) 4.dp else 20.dp
                ),
                shadowElevation = 2.dp,
                modifier = Modifier.widthIn(max = 280.dp)
            ) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(12.dp),
                    color = if (message.isFromUser) Color.White else Color(0xFF2C3E50),
                    fontSize = 16.sp
                )
            }

            if (message.isFromUser) {
                Surface(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(start = 8.dp),
                    shape = CircleShape,
                    color = Color(0xFFE8EAF6)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "U",
                            color = Color(0xFF6AB7C3),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}