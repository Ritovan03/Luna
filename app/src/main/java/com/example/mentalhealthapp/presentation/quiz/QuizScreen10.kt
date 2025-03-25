package com.example.mentalhealthapp.presentation.quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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

enum class Routines(val text: String) {
    CLEAR_STRUCTURED_PLAN("I prefer a clear, structured plan"),
    GO_WITH_FLOW("I go with the flow"),
    OCCASIONAL_REMINDERS("I need occasional reminders"),
    MOOD_BASED_PRIORITY("I like to prioritize based on my mood")
}

@Composable
fun QuizScreen10(navController: NavHostController) {
    var selectedOption by rememberSaveable { mutableStateOf<Routines?>(null) }

    val colors = remember {
        mapOf(
            "background" to Color(0xFFF8F5F1),
            "selectedGreen" to Color(0xFF8DB06F),
            "unselectedCard" to Color.White,
            "selectedBrown" to Color(0xFF64422C),
            "textBrown" to Color(0xFF5A3C2C),
            "progressBackground" to Color(0xFFE8D0C0)
        )
    }

    Scaffold(
        containerColor = colors["background"]!!,
        bottomBar = {
            ContinueButton(
                selectedOption = selectedOption,
                navController = navController,
                colors = colors
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top bar with back button and progress
                TopNavigationBar(
                    onBackClick = { navController.navigateUp() },
                    colors = colors
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Which of these best describes your approach to daily tasks?",
                    modifier = Modifier.fillMaxWidth(),
                    color = colors["textBrown"]!!,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Use a composable to create option rows for better code organization
                RoutineOptionRows(
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it },
                    colors = colors
                )
            }
        }
    }
}

@Composable
private fun TopNavigationBar(
    onBackClick: () -> Unit,
    colors: Map<String, Color>
) {
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
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_revert),
                contentDescription = "Back",
                tint = colors["textBrown"]!!
            )
        }

        Text(
            text = "Assessment",
            color = colors["textBrown"]!!,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(colors["progressBackground"]!!),
            color = colors["progressBackground"]!!
        ) {
            Text(
                text = "13 of 14",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                color = colors["textBrown"]!!,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun RoutineOptionRows(
    selectedOption: Routines?,
    onOptionSelected: (Routines) -> Unit,
    colors: Map<String, Color>
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OptionCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                title = Routines.CLEAR_STRUCTURED_PLAN.text,
                iconResId = android.R.drawable.ic_menu_sort_by_size,
                isSelected = selectedOption == Routines.CLEAR_STRUCTURED_PLAN,
                backgroundColor = if (selectedOption == Routines.CLEAR_STRUCTURED_PLAN)
                    colors["selectedGreen"]!! else colors["unselectedCard"]!!,
                borderColor = Color.Transparent,
                contentColor = if (selectedOption == Routines.CLEAR_STRUCTURED_PLAN)
                    Color.White else colors["textBrown"]!!,
                onClick = { onOptionSelected(Routines.CLEAR_STRUCTURED_PLAN) }
            )

            OptionCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                title = Routines.GO_WITH_FLOW.text,
                iconResId = android.R.drawable.ic_menu_help,
                isSelected = selectedOption == Routines.GO_WITH_FLOW,
                backgroundColor = if (selectedOption == Routines.GO_WITH_FLOW)
                    colors["selectedGreen"]!! else colors["unselectedCard"]!!,
                borderColor = Color.Transparent,
                contentColor = if (selectedOption == Routines.GO_WITH_FLOW)
                    Color.White else colors["textBrown"]!!,
                onClick = { onOptionSelected(Routines.GO_WITH_FLOW) }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OptionCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                title = Routines.OCCASIONAL_REMINDERS.text,
                iconResId = android.R.drawable.ic_menu_close_clear_cancel,
                isSelected = selectedOption == Routines.OCCASIONAL_REMINDERS,
                backgroundColor = if (selectedOption == Routines.OCCASIONAL_REMINDERS)
                    colors["selectedGreen"]!! else colors["unselectedCard"]!!,
                borderColor = Color.Transparent,
                contentColor = if (selectedOption == Routines.OCCASIONAL_REMINDERS)
                    Color.White else colors["textBrown"]!!,
                onClick = { onOptionSelected(Routines.OCCASIONAL_REMINDERS) }
            )

            OptionCard(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                title = Routines.MOOD_BASED_PRIORITY.text,
                iconResId = android.R.drawable.ic_menu_close_clear_cancel,
                isSelected = selectedOption == Routines.MOOD_BASED_PRIORITY,
                backgroundColor = if (selectedOption == Routines.MOOD_BASED_PRIORITY)
                    colors["selectedGreen"]!! else colors["unselectedCard"]!!,
                borderColor = Color.Transparent,
                contentColor = if (selectedOption == Routines.MOOD_BASED_PRIORITY)
                    Color.White else colors["textBrown"]!!,
                onClick = { onOptionSelected(Routines.MOOD_BASED_PRIORITY) }
            )
        }
    }
}

@Composable
private fun ContinueButton(
    selectedOption: Routines?,
    navController: NavHostController,
    colors: Map<String, Color>
) {
    Button(
        onClick = {
            runCatching {
                navController.navigate(Route.Quiz11.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }.onFailure {
                Log.e("QuizScreen10", "Navigation error", it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors["selectedBrown"]!!,
            contentColor = Color.White,
            disabledContainerColor = colors["selectedBrown"]!!.copy(alpha = 0.5f)
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