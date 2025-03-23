package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
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
import android.util.Log
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun QuizScreen9(navController: NavHostController) {
    val backgroundColor = Color(0xFFF8F5F1) // Light beige background
    val brownColor = Color(0xFF64422C) // Brown color for continue button and text
    val orangeColor = Color(0xFFE67E22) // Orange color for selected number

    // State for selected stress level
    var selectedLevel by remember { mutableStateOf(2) }

    // Stress level descriptions
    val stressDescriptions = mapOf(
        1 to "You Are Managing Your Stress Well.",
        2 to "You Have Mild Stress.",
        3 to "You Have Moderate Stress.",
        4 to "You Are Very Stressed.",
        5 to "You Are Extremely Stressed Out."
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
            // Top bar with back button and progress
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { /* Handle back navigation */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_revert),
                        contentDescription = "Back",
                        tint = brownColor
                    )
                }

                // Title
                Text(
                    text = "Assessment",
                    color = brownColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                // Progress indicator
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFE8D0C0)),
                    color = Color(0xFFE8D0C0)
                ) {
                    Text(
                        text = "12 of 14",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = brownColor,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "How would you rate your stress level?",
                modifier = Modifier.fillMaxWidth(),
                color = brownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Large number display
            Text(
                text = "$selectedLevel",
                color = brownColor,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 120.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Number selector
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

            // Stress level description
            Text(
                text = stressDescriptions[selectedLevel] ?: "",
                color = brownColor,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        // Continue button
        Button(
            onClick = {
                // Log selected stress level
                Log.d("StressLevel", "Selected stress level: $selectedLevel")
                // Navigate to quiz10
                navController.navigate(Route.Quiz10.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
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