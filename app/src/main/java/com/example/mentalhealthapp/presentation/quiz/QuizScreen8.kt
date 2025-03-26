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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun QuizScreen8(navController: NavHostController) {
    val backgroundColor = Color(0xFFF8F5F1) // Light beige background
    val greenColor = Color(0xFF8DB06F) // Light green color for input box
    val brownColor = Color(0xFF64422C) // Brown color for continue button
    val textBrownColor = Color(0xFF5A3C2C) // Brown color for text
    val orangeColor = Color(0xFFE67E22) // Orange color for chips

    // State for input field
    var symptomInput by remember { mutableStateOf("") }

    // State for symptoms list
    var symptoms by remember {
        mutableStateOf(
            listOf("Social Withdrawal", "Feeling Numbness", "Feeling Sad", "Depressed")
        )
    }

    // State for common symptoms
    var commonSymptoms by remember { mutableStateOf(listOf("Depressed", "Angry")) }

    // Calculate the count of symptoms (max 10)
    val symptomsCount = symptoms.size
    val maxSymptoms = 10

    // Keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current

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
                        text = "8 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Do you have other mental health symptoms?",
                modifier = Modifier.fillMaxWidth(),
                color = textBrownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Crying woman image
            Box(
                modifier = Modifier
                    .size(200.dp) // Increased from 160.dp
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.quiz8),
                    contentDescription = "Mental Health Symptoms",
                    modifier = Modifier.fillMaxSize(), // Fill the entire box
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Input box with chips
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // Increased from 200.dp
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                border = BorderStroke(1.dp, greenColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .fillMaxWidth()
                                .padding(bottom = 8.dp) // Added padding to prevent overlap
                        ) {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                mainAxisSpacing = 8.dp,
                                crossAxisSpacing = 12.dp // Increased from 8.dp for better spacing
                            ) {
                                symptoms.forEach { symptom ->
                                    AssistChip(
                                        onClick = { },
                                        label = { Text(symptom) },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = greenColor.copy(alpha = 0.2f),
                                            labelColor = greenColor
                                        ),
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Remove",
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clickable {
                                                        symptoms = symptoms.filter { it != symptom }
                                                    },
                                                tint = greenColor
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }

                    OutlinedTextField(
                        value = symptomInput,
                        onValueChange = { symptomInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        placeholder = { Text("Type a symptom...") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            containerColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (symptomInput.isNotBlank() && symptoms.size < maxSymptoms) {
                                    symptoms = symptoms + symptomInput.trim()
                                    symptomInput = ""
                                }
                                keyboardController?.hide()
                            }
                        ),
                        singleLine = true
                    )

                    // Counter
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_dialog_info),
                                contentDescription = "Info",
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$symptomsCount/$maxSymptoms",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Most Common section

        }

        // Continue button
        Button(
            onClick = {
                // Log symptoms
                Log.d("MentalHealthSymptoms", "Selected Symptoms: ${symptoms.joinToString(", ")}")
                // Navigate to quiz9
                navController.navigate(Route.Quiz9.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter),
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

// A simple row that wraps its content
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    mainAxisSpacing: Dp = 0.dp,
    crossAxisSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val sequences = mutableListOf<List<androidx.compose.ui.layout.Placeable>>()
        val crossAxisSizes = mutableListOf<Int>()
        val crossAxisPositions = mutableListOf<Int>()

        var mainAxisSpace = 0
        var crossAxisSpace = 0

        val currentSequence = mutableListOf<androidx.compose.ui.layout.Placeable>()
        var currentMainAxisSize = 0
        var currentCrossAxisSize = 0

        for (i in measurables.indices) {
            val placeable = measurables[i].measure(constraints)

            if (currentMainAxisSize + mainAxisSpace + placeable.width > constraints.maxWidth) {
                sequences += currentSequence.toList()
                crossAxisSizes += currentCrossAxisSize
                crossAxisPositions += crossAxisSpace

                crossAxisSpace += currentCrossAxisSize + crossAxisSpacing.roundToPx()
                mainAxisSpace = 0

                currentSequence.clear()
                currentMainAxisSize = 0
                currentCrossAxisSize = 0
            }

            currentSequence.add(placeable)
            currentMainAxisSize += mainAxisSpace + placeable.width
            mainAxisSpace = mainAxisSpacing.roundToPx()
            currentCrossAxisSize = maxOf(currentCrossAxisSize, placeable.height)
        }

        if (currentSequence.isNotEmpty()) {
            sequences += currentSequence.toList()
            crossAxisSizes += currentCrossAxisSize
            crossAxisPositions += crossAxisSpace
        }

        val mainAxisSize = if (sequences.isNotEmpty()) {
            sequences.maxOf { it.sumOf { placeable -> placeable.width } + (it.size - 1) * mainAxisSpacing.roundToPx() }
        } else {
            0
        }

        val crossAxisSize = crossAxisSpace + currentCrossAxisSize

        layout(mainAxisSize, crossAxisSize) {
            sequences.forEachIndexed { i, sequence ->
                var currentMainAxisPosition = 0

                sequence.forEach { placeable ->
                    placeable.place(
                        x = currentMainAxisPosition,
                        y = crossAxisPositions[i]
                    )
                    currentMainAxisPosition += placeable.width + mainAxisSpacing.roundToPx()
                }
            }
        }
    }
}