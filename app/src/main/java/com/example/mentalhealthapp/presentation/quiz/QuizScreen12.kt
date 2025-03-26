package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.foundation.BorderStroke
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
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route

enum class HyperfocusOption {
    RARELY,
    OCCASIONALLY,
    FREQUENTLY,
    ALL_TIME,
    NONE
}

@Composable
fun QuizScreen12(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf(HyperfocusOption.NONE) }

    val backgroundColor = Color(0xFFF8F5F1)
    val selectedGreenColor = Color(0xFF8DB06F)
    val unselectedCardColor = Color.White
    val selectedBrownColor = Color(0xFF64422C)
    val textBrownColor = Color(0xFF5A3C2C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar
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
                        text = "12 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "How often do you hyperfocus on something to the point of losing track of time?",
                modifier = Modifier.fillMaxWidth(),
                color = textBrownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                listOf(
                    HyperfocusOption.RARELY to "Rarely or never",
                    HyperfocusOption.OCCASIONALLY to "Occasionally, when I'm very interested",
                    HyperfocusOption.FREQUENTLY to "Frequently, especially when I'm emotionally invested",
                    HyperfocusOption.ALL_TIME to "All the time—I completely zone in"
                ).forEach { (option, text) ->
                    OptionCard12(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        title = text,
                        iconResId = android.R.drawable.ic_menu_sort_by_size,
                        isSelected = selectedOption == option,
                        backgroundColor = if (selectedOption == option) selectedGreenColor else unselectedCardColor,
                        borderColor = Color.Transparent,
                        contentColor = if (selectedOption == option) Color.White else textBrownColor,
                        onClick = { selectedOption = option }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Route.Quiz13.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedBrownColor,
                    contentColor = Color.White,
                    disabledContainerColor = selectedBrownColor.copy(alpha = 0.5f)
                ),
                enabled = selectedOption != HyperfocusOption.NONE,
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
private fun OptionCard12(
    modifier: Modifier = Modifier,
    title: String,
    iconResId: Int,
    isSelected: Boolean,
    backgroundColor: Color,
    borderColor: Color,
    contentColor: Color,
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
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                color = contentColor,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}