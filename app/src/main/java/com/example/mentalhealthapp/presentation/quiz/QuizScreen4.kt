package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mentalhealthapp.presentation.Navigation.Route
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen4(navController: NavHostController) {
    var isKgSelected by remember { mutableStateOf(true) }
    var sliderPosition by remember { mutableStateOf(58f) } // Default 58kg

    // Convert slider position to the displayed weight
    val displayWeight = remember(sliderPosition, isKgSelected) {
        if (isKgSelected) {
            sliderPosition.roundToInt()
        } else {
            (sliderPosition * 2.20462f).roundToInt()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F5F2))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Assessment Header
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
                        text = "4 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Question Text
            Text(
                text = "What's your weight?",
                color = Color(0xFF3E3A33),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Unit Selection Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color(0xFFFFFFFF))
                    .height(48.dp)
            ) {
                // kg Button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(28.dp))
                        .background(if (isKgSelected) Color(0xFFF4932F) else Color.Transparent)
                        .clickable { isKgSelected = true }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "kg",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isKgSelected) Color.White else Color(0xFF65635F)
                    )
                }

                // lbs Button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(28.dp))
                        .background(if (!isKgSelected) Color(0xFFF4932F) else Color.Transparent)
                        .clickable { isKgSelected = false }
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "lbs",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (!isKgSelected) Color.White else Color(0xFF65635F)
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Weight Display
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = displayWeight.toString(),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2A14)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = if (isKgSelected) "kg" else "lbs",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF3E2A14)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Weight Slider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                // Background ticks
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (i in 0..20) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(if (i % 5 == 0) 16.dp else 8.dp)
                                .background(Color(0xFFE8DED5))
                        )
                    }
                }

                // The slider
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    valueRange = 40f..150f,
                    steps = 110,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF9CAD7F),
                        activeTrackColor = Color(0xFF9CAD7F),
                        inactiveTrackColor = Color.Transparent
                    ),
                    thumb = {
                        Box(
                            modifier = Modifier
                                .size(16.dp, 48.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF9CAD7F))
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // Weight labels
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val minWeight = if (isKgSelected) 40 else 88
                    val maxWeight = if (isKgSelected) 150 else 330
                    val step = if (isKgSelected) 1 else 2
                    val labelsToShow = listOf(
                        (sliderPosition - 2 * step).roundToInt(),
                        (sliderPosition - step).roundToInt(),
                        sliderPosition.roundToInt(),
                        (sliderPosition + step).roundToInt(),
                        (sliderPosition + 2 * step).roundToInt()
                    ).filter { it in minWeight..maxWeight }

                    for (i in 0..4) {
                        if (i < labelsToShow.size) {
                            Text(
                                text = labelsToShow[i].toString(),
                                fontSize = 14.sp,
                                color = Color(0xFFA39F99),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(40.dp)
                            )
                        } else {
                            Spacer(modifier = Modifier.width(40.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Continue Button
            // Continue Button
            Button(
                onClick = {
                    navController.navigate(Route.Quiz5.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
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
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightSelectionScreen(
    onContinue: (Float, Boolean) -> Unit
) {

 */

