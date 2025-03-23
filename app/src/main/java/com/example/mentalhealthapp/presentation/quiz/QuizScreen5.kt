package com.example.mentalhealthapp.presentation.quiz

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.mentalhealthapp.R
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen5(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back Button
                IconButton(
                    onClick = { /* Navigate back */ },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Screen Title
                Text(
                    text = "Assessment",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.weight(1f))

                // Progress Indicator
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "8 of 14",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }

            val backgroundColor = Color(0xFFF7F4F2)
            val textColor = Color(0xFF4F3422)
            val mutedColor = Color(0xFFACA9A5)
            val pillColor = Color(0xFFE8DDD9)
            val sliderColor = Color(0xFFE8DDD9)
            val sliderHandleColor = Color(0xFFED7E1C)
            val sliderHandleStrokeColor = Color(0xFFC96100)
            val progressBarColor = Color(0xFFED7E1C)

            val options = listOf(
                SleepQualityOption("Excellent", "7-9 HOURS", Color(0xFF9BB168), R.drawable.iconlogo),
                SleepQualityOption("Good", "6-7 HOURS", Color(0xFFFFCE5C), R.drawable.iconlogo),
                SleepQualityOption("Fair", "5 HOURS", Color(0xFFC0A091), R.drawable.iconlogo),
                SleepQualityOption("Poor", "3-4 HOURS", Color(0xFFED7E1C), R.drawable.iconlogo),
                SleepQualityOption("Worst", "<3 HOURS", Color(0xFFA694F5), R.drawable.iconlogo)
            )

            // Animation scope for smooth transitions
            val coroutineScope = rememberCoroutineScope()

            // Use animatable for smooth scrolling
            var selectedOptionIndexFloat by remember { mutableStateOf(3f) } // Default to "Poor"
            var selectedOptionIndex by remember { mutableStateOf(3) } // Default to "Poor"

            // Calculate progress value based on position (inverted: 0 = worst, 1 = excellent)
            val progressValue = (options.size - 1 - selectedOptionIndex) / (options.size - 1f)

            // Question text - moved outside the slider box
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

            // Slider and options container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Use weight instead of fixed height
            ) {
                val density = LocalDensity.current
                // Adjust the slider height to fit the screen properly
                val sliderHeight = 340.dp
                val sliderHeightPx = with(density) { sliderHeight.toPx() }
                val optionSpacing = sliderHeightPx / (options.size - 1)

                // Store drag offset for smoother movement
                var dragOffset by remember { mutableStateOf(0f) }
                var isDragging by remember { mutableStateOf(false) }

                // Custom progress bar (track + filled portion)
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(sliderHeight)
                        .clip(RoundedCornerShape(4.dp))
                        .background(sliderColor)
                        .align(Alignment.Center)
                ) {
                    // Filled portion of progress bar
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .fillMaxHeight(progressValue) // Fill based on progress
                            .align(Alignment.BottomCenter)
                            .clip(RoundedCornerShape(4.dp))
                            .background(progressBarColor)
                    )
                }

                // Options layout with improved spacing
                options.forEachIndexed { index, option ->
                    val yPosition = (sliderHeightPx / (options.size - 1)) * index
                    val yOffset = with(density) { yPosition.toDp() - (sliderHeight / 2) }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = yOffset),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left side content (text and hours)
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = option.label,
                                color = if (index == selectedOptionIndex) textColor else mutedColor,
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
                                    tint = if (index == selectedOptionIndex) textColor else mutedColor,
                                    modifier = Modifier.size(20.dp)
                                )

                                Text(
                                    text = option.hours,
                                    color = if (index == selectedOptionIndex) textColor else mutedColor,
                                    fontSize = 18.sp
                                )
                            }
                        }

                        // Right side with emoji
                        Image(
                            painter = painterResource(id = option.emojiResId),
                            contentDescription = option.label,
                            modifier = Modifier
                                .size(48.dp)
                                .alpha(if (index == selectedOptionIndex) 1f else 0.4f)
                        )
                    }
                }

                // Draggable area for better touch control
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(sliderHeight)
                        .align(Alignment.Center)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = {
                                    isDragging = true
                                    dragOffset = 0f
                                },
                                onDragEnd = {
                                    isDragging = false
                                    // Smooth animation to nearest option
                                    coroutineScope.launch {
                                        // Animate to the nearest index
                                        dragOffset = 0f
                                    }
                                },
                                onDragCancel = {
                                    isDragging = false
                                    dragOffset = 0f
                                },
                                onDrag = { change, dragAmount ->
                                    change.consume()

                                    // Add drag amount to offset with some damping for smoother feel
                                    dragOffset += dragAmount.y * 0.9f

                                    // Calculate new position based on drag
                                    val totalOffset = (selectedOptionIndex * optionSpacing) + dragOffset
                                    val newIndexFloat = totalOffset / optionSpacing

                                    // Update selected index if it changed and in bounds
                                    val newIndex = newIndexFloat.roundToInt().coerceIn(0, options.size - 1)

                                    // Store floating point index for smoother transitions
                                    selectedOptionIndexFloat = newIndexFloat.coerceIn(0f, (options.size - 1).toFloat())

                                    if (newIndex != selectedOptionIndex) {
                                        selectedOptionIndex = newIndex
                                        // Reset drag offset partially after snapping to new index for smoother feel
                                        dragOffset *= 0.5f
                                    }

                                    // If drag goes too far from current selected index, dampen the movement
                                    if (abs(dragOffset) > optionSpacing * 0.6f) {
                                        dragOffset *= 0.9f
                                    }
                                }
                            )
                        }
                )

                // Slider handle with stem
                Box(
                    modifier = Modifier
                        .offset(
                            y = with(density) {
                                (selectedOptionIndex * optionSpacing + dragOffset * 0.2f).toDp() - (sliderHeight / 2)
                            }
                        )
                        .align(Alignment.Center)
                ) {
                    // Handle
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(sliderHandleColor)
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        // White circle in the middle of the handle
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                    }

                    // Add stem line to the right
                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(4.dp)
                            .background(sliderHandleColor)
                            .align(Alignment.CenterEnd)
                            .offset(x = (-28).dp) // Position it before the emoji face
                    )
                }
            }
        }
        Button(
            onClick = {
                navController.navigate(Route.Quiz6.route)
                Log.d("QuizScreen5", "screen5 button clicked")
            },
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

data class SleepQualityOption(
    val label: String,
    val hours: String,
    val color: Color,
    val emojiResId: Int
)

@Preview
@Composable
fun QuizScreen5Preview() {
    QuizScreen5(NavHostController(LocalContext.current))
}