package com.example.mentalhealthapp.presentation.quiz

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun QuizScreen3(navController: NavHostController) {
    val minAge = 12
    val maxAge = 45
    val initialAge = 18

    val density = LocalDensity.current
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = initialAge - minAge - 1)
    val coroutineScope = rememberCoroutineScope()

    var selectedAge by remember { mutableStateOf(initialAge) }
    var itemHeight by remember { mutableStateOf(0.dp) }
    val localItemHeight = remember { mutableStateOf(0) }

    // Calculate which item is in the center
    val centerItemIndex = remember {
        derivedStateOf {
            val firstVisibleItem = lazyListState.firstVisibleItemItem
            val firstVisibleItemOffset = lazyListState.firstVisibleItemScrollOffset

            if (localItemHeight.value == 0) return@derivedStateOf initialAge - minAge

            val center = firstVisibleItem + (firstVisibleItemOffset.toFloat() / localItemHeight.value.toFloat()).toInt() + 1
            center.coerceIn(0, maxAge - minAge)
        }
    }

    // Replace the LaunchedEffect with this updated version
    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress && lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty()) {
            // Calculate the closest item to the center
            val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
            val center = lazyListState.layoutInfo.viewportSize.height / 2

            val closestItem = visibleItems.minByOrNull {
                Math.abs((it.offset + it.size / 2) - center)
            } ?: return@LaunchedEffect

            selectedAge = closestItem.index + minAge

            // Only snap if the item isn't already centered
            if (Math.abs((closestItem.offset + closestItem.size / 2) - center) > 10) {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(
                        closestItem.index,
                        scrollOffset = -(center - closestItem.size / 2)
                    )
                }
            }
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
            // Top bar with back button and title
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
                        text = "3 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Question Text
            Text(
                text = "What's your age?",
                color = Color(0xFF3E3A33),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Age Picker
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                // This is our actual list of ages
                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(vertical = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(maxAge - minAge + 1) { index ->
                        val age = minAge + index
                        val isCenterItem = index == centerItemIndex.value

                        // Calculate scaling and alpha based on distance from center
                        val scale = if (isCenterItem) 1.4f else 1.0f
                        val alpha = if (isCenterItem) 1.0f else 0.4f

                        // Animate scale and alpha changes
                        val animatedScale = animateFloatAsState(targetValue = scale, label = "scale").value
                        val animatedAlpha = animateFloatAsState(targetValue = alpha, label = "alpha").value

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    localItemHeight.value = coordinates.size.height
                                    with(density) { itemHeight = coordinates.size.height.toDp() }
                                }
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .scale(animatedScale)
                                    .alpha(animatedAlpha)
                                    .let {
                                        if (isCenterItem) {
                                            it.clip(RoundedCornerShape(32.dp))
                                                .background(Color(0xFF9CAD7F))
                                                .padding(horizontal = 40.dp, vertical = 8.dp)
                                        } else {
                                            it
                                        }
                                    }
                            ) {
                                Text(
                                    text = age.toString(),
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCenterItem) Color.White else Color.Gray,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            // Continue Button
            Button(
                onClick = {
                    navController.navigate(Route.Quiz4.route)
                    Log.d("QuizScreen2", "${selectedAge} button clicked")
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

// Extension properties for LazyListState
val LazyListState.firstVisibleItemItem: Int
    get() = this.firstVisibleItemIndex

val LazyListState.firstVisibleItemScrollOffset: Int
    get() = this.firstVisibleItemScrollOffset
