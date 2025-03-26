package com.example.mentalhealthapp.presentation.quiz

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen5(navController: NavHostController) {
    // Colors from QuizScreen8
    val backgroundColor = Color(0xFFF8F5F1) // Light beige background
    val greenColor = Color(0xFF8DB06F) // Light green color
    val brownColor = Color(0xFF64422C) // Brown color for continue button
    val textBrownColor = Color(0xFF5A3C2C) // Brown color for text
    val orangeColor = Color(0xFFE67E22) // Orange color (can use for slider)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
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
                        text = "5 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            val textColor = textBrownColor
            val mutedColor = textBrownColor.copy(alpha = 0.5f)
            val sliderColor = Color(0xFFE8DDD9)
            val sliderHandleColor = orangeColor

            val options = listOf(
                SleepQualityOption("Excellent", "7-9 HOURS", Color(0xFF9BB168), R.drawable.excellent),
                SleepQualityOption("Good", "6-7 HOURS", Color(0xFFFFCE5C), R.drawable.good),
                SleepQualityOption("Fair", "5 HOURS", Color(0xFFC0A091), R.drawable.fair),
                SleepQualityOption("Poor", "3-4 HOURS", Color(0xFFED7E1C), R.drawable.poor),
                SleepQualityOption("Worst", "<3 HOURS", Color(0xFFA694F5), R.drawable.worst)
            )

            // Question text with updated brown color
            Text(
                text = "How would you rate your sleep quality?",
                color = textColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 60.dp)
            )

            // Main content with slider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                val coroutineScope = rememberCoroutineScope()

                // Start with "Worst" (index 4)
                var sliderPositionRaw by remember { mutableStateOf(4f) }

                // Animate the slider position for smooth movement
                val sliderPosition by animateFloatAsState(
                    targetValue = sliderPositionRaw,
                    animationSpec = spring(
                        dampingRatio = 0.85f,
                        stiffness = Spring.StiffnessLow  // Reduced stiffness for slower animation
                    ),
                    label = "sliderPosition"
                )

                val selectedIndex = sliderPosition.roundToInt().coerceIn(0, options.size - 1)

                // Calculate content height and option spacing
                val contentHeight = 380.dp

                // Adjusted padding for perfect track length
                val trackPadding = 22.dp
                val trackHeight = contentHeight - (trackPadding * 2)

                // Layout the options, slider, and emojis
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left side - Options text
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(contentHeight),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Render options from top to bottom
                        options.forEachIndexed { index, option ->
                            Column {
                                Text(
                                    text = option.label,
                                    color = if (index == selectedIndex) textColor else mutedColor,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_home),
                                        contentDescription = "Clock",
                                        tint = if (index == selectedIndex) textColor else mutedColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = option.hours,
                                        color = if (index == selectedIndex) textColor else mutedColor,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }

                    // Center - Custom vertical slider
                    Box(
                        modifier = Modifier
                            .width(64.dp)
                            .height(contentHeight),
                        contentAlignment = Alignment.Center
                    ) {
                        // Track background
                        Box(
                            modifier = Modifier
                                .width(8.dp)
                                .height(trackHeight)
                                .clip(RoundedCornerShape(4.dp))
                                .background(sliderColor)
                                .align(Alignment.Center)
                        )

                        // Clickable area for the entire slider track
                        Box(
                            modifier = Modifier
                                .width(64.dp)
                                .height(trackHeight)
                                .align(Alignment.Center)
                                .pointerInput(Unit) {
                                    detectTapGestures { offset ->
                                        // Calculate new position based on tap position
                                        val percentage = offset.y / size.height
                                        val newPositionValue = percentage * (options.size - 1)

                                        // Update slider position with the tapped position
                                        sliderPositionRaw = newPositionValue.coerceIn(0f, (options.size - 1).toFloat())
                                    }
                                }
                        )

                        // Calculate handle position
                        val handleOffset = if (options.size > 1) {
                            val positionRatio = sliderPosition / (options.size - 1)
                            val trackOffset = trackHeight * positionRatio
                            trackOffset - (trackHeight / 2)
                        } else {
                            0.dp
                        }

                        // Draggable handle
                        Box(
                            modifier = Modifier
                                .offset(y = handleOffset)
                                .draggable(
                                    orientation = Orientation.Vertical,
                                    state = rememberDraggableState { delta ->
                                        // Adjusted sensitivity for the new track length
                                        val dragSensitivity = 0.005f
                                        val newPosition = sliderPositionRaw + (delta * dragSensitivity)

                                        // Make sure we stay within bounds
                                        sliderPositionRaw = newPosition.coerceIn(
                                            0f,
                                            (options.size - 1).toFloat()
                                        )
                                    },
                                    onDragStopped = {
                                        // Snap to nearest position when drag stops
                                        coroutineScope.launch {
                                            // Animate to the nearest integer position
                                            sliderPositionRaw = sliderPositionRaw.roundToInt().toFloat()
                                        }
                                    }
                                )
                        ) {
                            // Orange hollow circle handle (using updated orangeColor)
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(sliderHandleColor)
                                    .border(3.dp, sliderHandleColor, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                // Inner white circle (creating the hollow effect)
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .border(1.dp, Color.White, CircleShape)
                                )

                                // Smaller orange circle in the center for a more refined look
                                Box(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clip(CircleShape)
                                        .background(sliderHandleColor)
                                )
                            }
                        }
                    }

                    // Right side - Emoji icons
                    Column(
                        modifier = Modifier
                            .width(48.dp)
                            .height(contentHeight),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Render emojis from top to bottom
                        options.forEachIndexed { index, option ->
                            Image(
                                painter = painterResource(id = option.emojiResId),
                                contentDescription = option.label,
                                modifier = Modifier
                                    .size(48.dp)
                                    .alpha(if (index == selectedIndex) 1f else 0.4f)
                            )
                        }
                    }
                }
            }

            // Continue button with QuizScreen8 styling
            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            Button(
                onClick = {
                    navController.navigate(Route.Quiz6.route)
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

data class SleepQualityOption(
    val label: String,
    val hours: String,
    val color: Color,
    val emojiResId: Int
)