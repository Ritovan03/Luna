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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.NotInterested
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


// Define the option selections
enum class MedicationOption {
    PRESCRIBED,
    OVER_THE_COUNTER,
    NOT_TAKING_ANY,
    PREFER_NOT_TO_SAY,
    NONE,
}


@Composable
fun QuizScreen6(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf(MedicationOption.NONE) }

    val backgroundColor = Color(0xFFF8F5F1) // Light beige background color
    val selectedGreenColor = Color(0xFF8DB06F) // Green color for OTC option
    val unselectedCardColor = Color.White
    val selectedBrownColor = Color(0xFF64422C) // Brown color for continue button
    val textBrownColor = Color(0xFF5A3C2C) // Brown color for text

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                        tint = textBrownColor
                    )
                }

                // Title
                Text(
                    text = "Assessment",
                    color = textBrownColor,
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
                        text = "9 of 14",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = textBrownColor,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Question text
            Text(
                text = "Are you taking any medications?",
                modifier = Modifier.fillMaxWidth(),
                color = textBrownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Options grid with larger buttons
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OptionCard(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.85f), // Makes buttons taller
                        title = "Prescribed Medications",
                        iconResId = android.R.drawable.ic_menu_sort_by_size,
                        isSelected = selectedOption == MedicationOption.PRESCRIBED,
                        backgroundColor = if (selectedOption == MedicationOption.PRESCRIBED)
                            selectedGreenColor else unselectedCardColor,
                        borderColor = Color.Transparent,
                        contentColor = if (selectedOption == MedicationOption.PRESCRIBED)
                            Color.White else textBrownColor,
                        onClick = { selectedOption = MedicationOption.PRESCRIBED }
                    )

                    OptionCard(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.85f),
                        title = "Over the Counter Supplements",
                        iconResId = android.R.drawable.ic_menu_help,
                        isSelected = selectedOption == MedicationOption.OVER_THE_COUNTER,
                        backgroundColor = if (selectedOption == MedicationOption.OVER_THE_COUNTER)
                            selectedGreenColor else unselectedCardColor,
                        borderColor = Color.Transparent,
                        contentColor = if (selectedOption == MedicationOption.OVER_THE_COUNTER)
                            Color.White else textBrownColor,
                        onClick = { selectedOption = MedicationOption.OVER_THE_COUNTER }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OptionCard(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.85f),
                        title = "I'm not taking any",
                        iconResId = android.R.drawable.ic_menu_close_clear_cancel,
                        isSelected = selectedOption == MedicationOption.NOT_TAKING_ANY,
                        backgroundColor = if (selectedOption == MedicationOption.NOT_TAKING_ANY)
                            selectedGreenColor else unselectedCardColor,
                        borderColor = Color.Transparent,
                        contentColor = if (selectedOption == MedicationOption.NOT_TAKING_ANY)
                            Color.White else textBrownColor,
                        onClick = { selectedOption = MedicationOption.NOT_TAKING_ANY }
                    )

                    OptionCard(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(0.85f),
                        title = "Prefer not to say",
                        iconResId = android.R.drawable.ic_menu_close_clear_cancel,
                        isSelected = selectedOption == MedicationOption.PREFER_NOT_TO_SAY,
                        backgroundColor = if (selectedOption == MedicationOption.PREFER_NOT_TO_SAY)
                            selectedGreenColor else unselectedCardColor,
                        borderColor = Color.Transparent,
                        contentColor = if (selectedOption == MedicationOption.PREFER_NOT_TO_SAY)
                            Color.White else textBrownColor,
                        onClick = { selectedOption = MedicationOption.PREFER_NOT_TO_SAY }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Continue button
            Button(
                onClick = {
                    if (selectedOption == MedicationOption.NOT_TAKING_ANY ||
                        selectedOption == MedicationOption.PREFER_NOT_TO_SAY
                    ) {
                        navController.navigate(Route.Quiz8.route)
                    } else if (selectedOption == MedicationOption.PRESCRIBED ||
                        selectedOption == MedicationOption.OVER_THE_COUNTER
                    ) {
                        navController.navigate(Route.Quiz7.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedBrownColor,
                    contentColor = Color.White,
                    disabledContainerColor = selectedBrownColor.copy(alpha = 0.5f)
                ),
                enabled = selectedOption != MedicationOption.NONE,
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

@Composable
fun OptionCard(
    modifier: Modifier = Modifier,
    title: String,
    iconResId: Int,
    isSelected: Boolean,
    backgroundColor: Color,
    borderColor: Color,
    contentColor: Color = Color(0xFF5A3C2C),
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        color = backgroundColor,
        border = BorderStroke(width = if (isSelected) 2.dp else 0.dp, color = borderColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                color = contentColor,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


