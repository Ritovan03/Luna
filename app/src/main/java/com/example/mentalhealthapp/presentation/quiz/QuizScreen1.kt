package com.example.mentalhealthapp.presentation.quiz


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp
import com.example.mentalhealthapp.R

@Composable
fun QuizScreen1(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7F5))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top bar with back button and title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                // Back button
                IconButton(
                    onClick = { /* Handle back navigation */ },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFFE0DDD9), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF4A4A4A)
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
                        text = "1 of 14",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Main question
            Text(
                text = "What's your health goal for using Luna?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A2B0F),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Option 1 - Reduce stress
            GoalOption(
                icon = R.drawable.ic_home,
                text = "I wanna reduce stress",
                isSelected = selectedOption == 0,
                onClick = { selectedOption = 0 }
            )

            // Option 2 - AI Therapy
            GoalOption(
                icon = R.drawable.ic_home,
                text = "I wanna try AI Therapy",
                isSelected = selectedOption == 1,
                onClick = { selectedOption = 1 }
            )

            // Option 3 - Cope with trauma
            GoalOption(
                icon = R.drawable.ic_home,
                text = "I want to cope with trauma",
                isSelected = selectedOption == 2,
                onClick = { selectedOption = 2 }
            )

            // Option 4 - Be a better person
            GoalOption(
                icon = R.drawable.ic_home,
                text = "I want to be a better person",
                isSelected = selectedOption == 3,
                onClick = { selectedOption = 3 }
            )

            // Option 5 - Trying out the app
            GoalOption(
                icon = R.drawable.ic_home,
                text = "Just trying out the app, mate!",
                isSelected = selectedOption == 4,
                onClick = { selectedOption = 4 }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Continue button
            Button(
                onClick = { /* Handle continue action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5D3D1E)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .clickable{navController.navigate(Route.Quiz2.route)}



                ) {
                    Text(
                        text = "Continue",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Continue",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun GoalOption(
    icon: Int,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color = if (isSelected) Color(0xFFA9C47F) else Color.White,
    textColor: Color = if (isSelected) Color.White else Color(0xFF4A4A4A),
    iconTint: Color = if (isSelected) Color.White else Color(0xFF8E8E8E)
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = textColor,
                modifier = Modifier.weight(1f)
            )

            RadioButton(
                selected = isSelected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = if (backgroundColor == Color.White) Color(0xFF5D3D1E) else Color.White,
                    unselectedColor = Color(0xFFD1D1D1)
                )
            )
        }
    }
}





