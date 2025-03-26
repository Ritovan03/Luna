package com.example.mentalhealthapp.presentation.quiz


import androidx.compose.runtime.Composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun QuizScreen15(navController: NavHostController) {
    val backgroundColor = Color(0xFFF8F5F1)
    val brownColor = Color(0xFF64422C)
    val orangeColor = Color(0xFFE67E22)

    var selectedLevel by remember { mutableStateOf(2) }

    val environmentDescriptions = mapOf(
        1 to "Silent Nature - Peaceful, no human noise. Just birds, rustling leaves, or flowing water.",
        2 to "Quiet Indoors - Soft background sounds like a ticking clock, low music, or a fan.",
        3 to "Lightly Busy Room - Chatter, people moving, occasional interruptions.",
        4 to "Crowded Indoor Area - Office with people talking, phones ringing, or a mildly chaotic workspace.",
        5 to "Loud, High-Stim Environments - Concerts, festivals, parties, or bustling markets."
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                // Back button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF65635F), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "(",
                        color = Color(0xFF65635F),
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Assessment title
                Text(
                    text = "Assessment",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(0xFF4A2B0F)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Page indicator
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFEAE0D5))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "15 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Up to which environment can you comfortably bear without feeling overwhelmed?",
                modifier = Modifier.fillMaxWidth(),
                color = brownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp  // Added line height for spacing between lines
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "$selectedLevel",
                color = brownColor,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 120.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 1..5) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(if (selectedLevel == i) orangeColor else Color.Transparent)
                            .clickable { selectedLevel = i },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i",
                            color = if (selectedLevel == i) Color.White else brownColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = environmentDescriptions[selectedLevel] ?: "",
                color = brownColor,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Continue button
        Button(
            onClick = {
                navController.navigate(Route.Home.route)
                Log.d("EnvironmentLevel", "Selected environment level: $selectedLevel")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter),  // Removed bottom padding
            colors = ButtonDefaults.buttonColors(
                containerColor = brownColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Continue"
                )
            }
        }
    }
}