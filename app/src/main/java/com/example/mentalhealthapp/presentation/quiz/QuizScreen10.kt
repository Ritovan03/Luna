package com.example.mentalhealthapp.presentation.quiz

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route
import androidx.compose.runtime.saveable.rememberSaveable


enum class Routines(val text: String) {
    CLEAR_STRUCTURED_PLAN("I prefer a clear, structured plan"),
    GO_WITH_FLOW("I go with the flow"),
    OCCASIONAL_REMINDERS("I need occasional reminders"),
    MOOD_BASED_PRIORITY("I like to prioritize based on my mood")
}

@Composable
fun QuizScreen10(navController: NavHostController) {
//    var selectedOption by remember { mutableStateOf<Routines?>(null) }
//
    val backgroundColor = Color(0xFFF8F5F1)
    val selectedGreenColor = Color(0xFF8DB06F)
    val unselectedCardColor = Color.White
    val selectedBrownColor = Color(0xFF64422C)
    val textBrownColor = Color(0xFF5A3C2C)
    val borderColor = Color(0xFF8DB06F)
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(backgroundColor)
//            .padding(16.dp)
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Top bar with back button and progress
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(CircleShape)
//                        .background(Color.White)
//                        .clickable { navController.popBackStack() },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = android.R.drawable.ic_menu_revert),
//                        contentDescription = "Back",
//                        tint = textBrownColor
//                    )
//                }
//
//                Text(
//                    text = "Assessment",
//                    color = textBrownColor,
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 16.sp
//                )
//
//                Surface(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(20.dp))
//                        .background(Color(0xFFE8D0C0)),
//                    color = Color(0xFFE8D0C0)
//                ) {
//                    Text(
//                        text = "13 of 14",
//                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
//                        color = textBrownColor,
//                        fontSize = 14.sp
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Text(
//                text = "Which of these best describes your approach to daily tasks?",
//                modifier = Modifier.fillMaxWidth(),
//                color = textBrownColor,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp,
//                textAlign = TextAlign.Center
//            )
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                OptionCard10(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1f),
//                    title = Routines.CLEAR_STRUCTURED_PLAN.text,
//                    iconResId = android.R.drawable.ic_menu_sort_by_size,
//                    isSelected = selectedOption == Routines.CLEAR_STRUCTURED_PLAN,
//                    backgroundColor = if (selectedOption == Routines.CLEAR_STRUCTURED_PLAN)
//                        selectedGreenColor else unselectedCardColor,
//                    borderColor = borderColor,
//                    contentColor = if (selectedOption == Routines.CLEAR_STRUCTURED_PLAN)
//                        Color.White else textBrownColor,
//                    onClick = { selectedOption = Routines.CLEAR_STRUCTURED_PLAN }
//                )
//
//                OptionCard10(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1f),
//                    title = Routines.GO_WITH_FLOW.text,
//                    iconResId = android.R.drawable.ic_menu_help,
//                    isSelected = selectedOption == Routines.GO_WITH_FLOW,
//                    backgroundColor = if (selectedOption == Routines.GO_WITH_FLOW)
//                        selectedGreenColor else unselectedCardColor,
//                    borderColor = borderColor,
//                    contentColor = if (selectedOption == Routines.GO_WITH_FLOW)
//                        Color.White else textBrownColor,
//                    onClick = { selectedOption = Routines.GO_WITH_FLOW }
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                OptionCard10(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1f),
//                    title = Routines.OCCASIONAL_REMINDERS.text,
//                    iconResId = android.R.drawable.ic_popup_reminder,
//                    isSelected = selectedOption == Routines.OCCASIONAL_REMINDERS,
//                    backgroundColor = if (selectedOption == Routines.OCCASIONAL_REMINDERS)
//                        selectedGreenColor else unselectedCardColor,
//                    borderColor = borderColor,
//                    contentColor = if (selectedOption == Routines.OCCASIONAL_REMINDERS)
//                        Color.White else textBrownColor,
//                    onClick = { selectedOption = Routines.OCCASIONAL_REMINDERS }
//                )
//
//                OptionCard10(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1f),
//                    title = Routines.MOOD_BASED_PRIORITY.text,
//                    iconResId = android.R.drawable.ic_menu_today,
//                    isSelected = selectedOption == Routines.MOOD_BASED_PRIORITY,
//                    backgroundColor = if (selectedOption == Routines.MOOD_BASED_PRIORITY)
//                        selectedGreenColor else unselectedCardColor,
//                    borderColor = borderColor,
//                    contentColor = if (selectedOption == Routines.MOOD_BASED_PRIORITY)
//                        Color.White else textBrownColor,
//                    onClick = { selectedOption = Routines.MOOD_BASED_PRIORITY }
//                )
//            }
//
//            Spacer(modifier = Modifier.weight(1f))

            // Continue button
            Button(
                onClick = {
//                    if (selectedOption != null) {
//                        try {
//                            Log.d("QuizScreen10", "Navigating to Quiz11 with option: ${selectedOption?.text}")
//                            // Try using the simplest navigation approach
                            navController.navigate(Route.Quiz11.route)
//                        } catch (e: Exception) {
//                            Log.e("QuizScreen10", "Navigation error: ${e.message}", e)
//                        }
//                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(bottom = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = selectedBrownColor,
                    contentColor = Color.White,
                    disabledContainerColor = selectedBrownColor.copy(alpha = 0.5f)
                ),
//                enabled = selectedOption != null,
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


////// Added dedicated OptionCard for QuizScreen10
////@Composable
////fun OptionCard10(
////    modifier: Modifier = Modifier,
////    title: String,
////    iconResId: Int,
////    isSelected: Boolean,
////    backgroundColor: Color,
////    borderColor: Color,
////    contentColor: Color = Color(0xFF5A3C2C),
////    onClick: () -> Unit
////) {
////    Surface(
////        modifier = modifier
////            .clip(RoundedCornerShape(16.dp))
////            .clickable { onClick() },
////        color = backgroundColor,
////        border = BorderStroke(width = if (isSelected) 2.dp else 0.dp, color = borderColor),
////        shape = RoundedCornerShape(16.dp)
////    ) {
////        Column(
////            modifier = Modifier
////                .padding(16.dp)
////                .fillMaxSize(),
////            horizontalAlignment = Alignment.CenterHorizontally,
////            verticalArrangement = Arrangement.Center
////        ) {
////            Icon(
////                painter = painterResource(id = iconResId),
////                contentDescription = title,
////                tint = contentColor,
////                modifier = Modifier.size(32.dp)
////            )
////
////            Spacer(modifier = Modifier.height(12.dp))
////
////            Text(
////                text = title,
////                color = contentColor,
////                fontWeight = FontWeight.Medium,
////                fontSize = 14.sp,
////                textAlign = TextAlign.Center
////            )
////        }
////    }
////}
