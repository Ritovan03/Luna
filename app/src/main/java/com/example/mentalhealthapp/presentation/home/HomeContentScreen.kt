package com.example.mentalhealthapp.presentation.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import kotlinx.coroutines.launch

data class Task(
    val title: String,
    val time: String,
    val priority: TaskPriority
)

enum class TaskPriority {
    HIGH, MEDIUM, LOW
}

data class MoodItem(
    val imageResId: Int,
    val moodName: String,
    val backgroundColor: Color
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContentScreen(navController: NavHostController, mainNavController: NavHostController) {
    val tasks = remember {
        mutableStateListOf(
            Task("Take medicine", "Today at 9:00 AM", TaskPriority.HIGH),
            Task("Meditation", "Today at 10:00 AM", TaskPriority.MEDIUM),
            Task("Exercise", "Today at 11:00 AM", TaskPriority.LOW),
            Task("Walk dog", "Today at 4:00 PM", TaskPriority.MEDIUM)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE6E6FA), shape = RoundedCornerShape(16.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Today's Tasks",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.White, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Task",
                            tint = Color.Black
                        )
                    }
                }

                tasks.forEach { task ->
                    TaskItem(task)
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { mainNavController.navigate(Route.Anxiety.route) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anxious_home),
                    contentDescription = "Feeling Anxious Button",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }


        item {
            val moods = listOf(
                MoodItem(R.drawable.mood_good, "Good", Color(0xFFFFDD6F)),
                MoodItem(R.drawable.mood_upset, "Upset", Color(0xFF8CA4EE)),
                MoodItem(R.drawable.mood_angry, "Angry", Color(0xFFFF843E)),
                MoodItem(R.drawable.mood_sad, "Sad", Color(0xFFA1E7EB)),
                MoodItem(R.drawable.mood_happy, "Happy", Color(0xFFDFEBFF)),
                MoodItem(R.drawable.mood_spectacular, "Spectacular", Color(0xFFFFA7BC))
            )

            var selectedMood by remember { mutableStateOf(moods[0]) }
            val pagerState = rememberPagerState(pageCount = { moods.size })
            val coroutineScope = rememberCoroutineScope()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) { page ->
                    val mood = moods[page]
                    val scale by animateFloatAsState(
                        targetValue = if (pagerState.currentPage == page) 1.2f else 1f,
                        label = "Mood Scale"
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                            .scale(scale)
                            .clickable { selectedMood = mood },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = mood.imageResId),
                            contentDescription = mood.moodName,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                MoodSelectionDots(
                    pagerState = pagerState,
                    moods = moods,
                    onDotClick = { index ->
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle mood selection */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = selectedMood.backgroundColor)
                ) {
                    Text(
                        text = "I'm feeling ${selectedMood.moodName}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

    }
}


@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            when (task.priority) {
                                TaskPriority.HIGH -> Color(0xFFFF6B6B)
                                TaskPriority.MEDIUM -> Color(0xFF4ECDC4)
                                TaskPriority.LOW -> Color(0xFFFFD93D)
                            },
                            CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = task.time,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = when (task.priority) {
                    TaskPriority.HIGH -> Color(0xFFFF6B6B)
                    TaskPriority.MEDIUM -> Color(0xFF4ECDC4)
                    TaskPriority.LOW -> Color(0xFFFFD93D)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MoodSelectionDots(
    pagerState: androidx.compose.foundation.pager.PagerState,
    moods: List<MoodItem>,
    onDotClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        moods.forEachIndexed { index, mood ->
            val scale by animateFloatAsState(
                targetValue = if (pagerState.currentPage == index) 1.5f else 1f,
                label = "Dot Scale"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (pagerState.currentPage == index) 16.dp else 12.dp)
                    .background(
                        color = mood.backgroundColor,
                        shape = CircleShape
                    )
                    .scale(scale)
                    .clickable { onDotClick(index) }
            )
        }
    }
}
