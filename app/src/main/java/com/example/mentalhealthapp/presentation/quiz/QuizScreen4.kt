package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun QuizScreen4(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Quiz 4", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { navController.navigate(Route.Quiz5.route) }) {
            Text("Next")

        }

    }
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightSelectionScreen(
    onContinue: (Float, Boolean) -> Unit
) {
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
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
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

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Assessment",
                    color = Color(0xFF65635F),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                Surface(
                    color = Color(0xFFE8DED5),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "4 of 14",
                        color = Color(0xFF65635F),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 14.sp
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
            Button(
                onClick = { onContinue(sliderPosition, isKgSelected) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3E3A33)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Continue",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

// Extension function to make Box clickable
private fun Modifier.clickable(onClick: () -> Unit): Modifier = this.then(
    androidx.compose.foundation.clickable(
        onClick = onClick,
        indication = null,
        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
    )
)
 */

