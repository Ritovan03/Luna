package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun QuizScreen14(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf<Boolean?>(null) }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_revert),
                        contentDescription = "Back",
                        tint = textBrownColor
                    )
                }

                Text(
                    text = "Assessment",
                    color = textBrownColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFE8D0C0)),
                    color = Color(0xFFE8D0C0)
                ) {
                    Text(
                        text = "14 of 14",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = textBrownColor,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Image
            Image(
                painter = painterResource(id = R.drawable.help_quiz),
                contentDescription = "Mental health illustration",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Have you sought professional help before?",
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
                OptionCard14(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),  // Reduced from 100.dp
                    title = "Yes",
                    iconResId = android.R.drawable.ic_menu_sort_by_size,
                    isSelected = selectedOption == true,
                    backgroundColor = if (selectedOption == true) selectedGreenColor else unselectedCardColor,
                    borderColor = Color.Transparent,
                    contentColor = if (selectedOption == true) Color.White else textBrownColor,
                    onClick = { selectedOption = true }
                )

                OptionCard14(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),  // Reduced from 100.dp
                    title = "No",
                    iconResId = android.R.drawable.ic_menu_sort_by_size,
                    isSelected = selectedOption == false,
                    backgroundColor = if (selectedOption == false) selectedGreenColor else unselectedCardColor,
                    borderColor = Color.Transparent,
                    contentColor = if (selectedOption == false) Color.White else textBrownColor,
                    onClick = { selectedOption = false }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Route.Quiz15.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedBrownColor,
                    contentColor = Color.White,
                    disabledContainerColor = selectedBrownColor.copy(alpha = 0.5f)
                ),
                enabled = selectedOption != null,
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
private fun OptionCard14(
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
            .clickable { onClick() },  // Fixed empty onClick
        color = backgroundColor,
        border = BorderStroke(width = if (isSelected) 2.dp else 0.dp, color = borderColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)  // Reduced from 16.dp to fit smaller height
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