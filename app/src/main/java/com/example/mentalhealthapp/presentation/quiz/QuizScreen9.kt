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
                        text = "9 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
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
        // Continue Button
        Button(
            onClick = {
                // Log selected stress level
                Log.d("StressLevel", "Selected stress level: $selectedLevel")
                navController.navigate(Route.Quiz10.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64422C),
                contentColor = Color.White,
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