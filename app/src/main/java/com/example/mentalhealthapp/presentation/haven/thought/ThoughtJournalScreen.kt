package com.example.mentalhealthapp.presentation.haven.thought

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.ui.theme.UrbanistFont
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

// Mood tags with appropriate colors for emotional identification
enum class MoodTag(val label: String, val color: Color) {
    CALM("Calm", Color(0xFF81C784)),     // Will use green_color resource
    GRATEFUL("Grateful", Color(0xFF64B5F6)), // Soft blue
    PROUD("Proud", Color(0xFFBA68C8)),     // Soft purple
    HAPPY("Happy", Color(0xFFFFD54F)),     // Soft yellow
    ANXIOUS("Anxious", Color(0xFFFFB74D)), // Soft orange
    OVERWHELMED("Overwhelmed", Color(0xFFE57373)), // Soft red
    STRESSED("Stressed", Color(0xFFFFCC80)), // Soft peach
    TIRED("Tired", Color(0xFF90A4AE))      // Soft gray-blue
}

// Data class for journal entries
data class JournalEntry(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val gratitudeNote: String,
    val timestamp: Date = Date(),
    val mood: MoodTag
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtJournalScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.offwhite_screen_color),
    ) { innerPadding ->
        ThoughtJournalContent(
            Modifier.padding(innerPadding),
            onBackButtonPressed = { navController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtJournalContent(modifier: Modifier = Modifier, onBackButtonPressed: () -> Unit) {
    // Sample journal entries for demonstration
    val journalEntries = remember {
        mutableStateListOf(
            JournalEntry(
                content = "Today was challenging. I had a sensory overload at the grocery store, but I managed to use my coping techniques. I'm proud of how I handled it.",
                gratitudeNote = "My noise-cancelling headphones",
                mood = MoodTag.PROUD
            ),
            JournalEntry(
                content = "Had a productive therapy session. We discussed strategies for managing work transitions. I feel more equipped now.",
                gratitudeNote = "My supportive therapist",
                mood = MoodTag.CALM
            ),
            JournalEntry(
                content = "Struggled with focus today. My mind kept wandering during the team meeting. Need to try the Pomodoro technique tomorrow.",
                gratitudeNote = "My colleague who shared their notes with me",
                mood = MoodTag.STRESSED
            )
        )
    }

    var showAddJournalSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var isRecording by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Header with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Changed to push content to ends
        ) {
            // Left side with back button and title
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF65635F), CircleShape)
                        .clickable { onBackButtonPressed() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "(",
                        color = Color(0xFF65635F),
                        fontSize = 18.sp
                    )
                }
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "Thought Journal",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.brown),
                    fontFamily = UrbanistFont,
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }

            // Right side with Lottie animation
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.thought_journal)
                )
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.Center)
                )
            }
        }

        // Main content - either empty state or journal entries
        if (journalEntries.isEmpty()) {
            EmptyJournalState(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            // Journal entries list
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(journalEntries, key = { it.id }) { entry ->
                    JournalEntryCard(entry)
                }

                // Extra space at bottom to avoid FAB overlap
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }

    // Add Journal Entry Bottom Sheet
    if (showAddJournalSheet) {
        ModalBottomSheet(
            onDismissRequest = { showAddJournalSheet = false },
            sheetState = sheetState
        ) {
            AddJournalEntryContent(
                onAddEntry = { content, gratitudeNote, mood ->
                    journalEntries.add(
                        JournalEntry(
                            content = content,
                            gratitudeNote = gratitudeNote,
                            mood = mood
                        )
                    )
                    scope.launch {
                        sheetState.hide()
                        showAddJournalSheet = false
                    }
                },
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                        showAddJournalSheet = false
                    }
                },
                isRecording = isRecording,
                onToggleRecording = { isRecording = it }
            )
        }
    }

    // FAB for adding new journal entry
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { showAddJournalSheet = true },
            containerColor = colorResource(R.color.green_color),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.Black,
                contentDescription = "Add Journal Entry",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun JournalEntryCard(entry: JournalEntry) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Date and mood tag row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Date
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = colorResource(R.color.brown),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    val dateFormat = SimpleDateFormat("MMM dd, yyyy • h:mm a", Locale.getDefault())
                    Text(
                        text = dateFormat.format(entry.timestamp),
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontStyle = FontStyle.Italic
                    )
                }

                // Mood tag
                MoodTagChip(entry.mood)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Journal content
            Text(
                text = entry.content,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.LightGray.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(12.dp))

            // Gratitude note with green background
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(id = R.color.green_color).copy(alpha = 0.15f))
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color(0xFFE57373), // Soft red for heart icon
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Grateful for: ${entry.gratitudeNote}",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun MoodTagChip(mood: MoodTag) {
    // Get the appropriate color - using green_color resource for CALM mood
    val moodColor = when (mood) {
        MoodTag.CALM -> colorResource(id = R.color.green_color)
        else -> mood.color
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(moodColor.copy(alpha = 0.2f))
            .border(
                width = 1.dp,
                color = moodColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = mood.label,
            fontSize = 12.sp,
            color = if (mood == MoodTag.CALM) colorResource(id = R.color.brown) else moodColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun EmptyJournalState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Journal illustration with green color
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(id = R.color.green_color).copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = colorResource(R.color.brown),
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your Journal Awaits",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = colorResource(R.color.brown)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Start capturing your thoughts and reflections.\nTap the + button to begin.",
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@Composable
fun AddJournalEntryContent(
    onAddEntry: (String, String, MoodTag) -> Unit,
    onDismiss: () -> Unit,
    isRecording: Boolean,
    onToggleRecording: (Boolean) -> Unit
) {
    var journalContent by remember { mutableStateOf("") }
    var gratitudeNote by remember { mutableStateOf("") }
    var selectedMood by remember { mutableStateOf(MoodTag.CALM) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "New Journal Entry",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.brown),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Date display
        val dateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
        Text(
            text = dateFormat.format(Date()),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Journal content input with voice recording option
        Column {
            Text(
                text = "What's on your mind?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.brown)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Main text input with recording button
            OutlinedTextField(
                value = journalContent,
                onValueChange = { journalContent = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = colorResource(R.color.brown),
                    cursorColor = colorResource(R.color.brown)
                ),
                placeholder = { Text("Write freely about your thoughts and feelings...") },
                trailingIcon = {
                    IconButton(
                        onClick = { onToggleRecording(!isRecording) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = if (isRecording) "Stop Recording" else "Start Voice Recording",
                            tint = if (isRecording) colorResource(id = R.color.green_color) else colorResource(
                                R.color.brown
                            )
                        )
                    }
                }
            )

            // Recording indicator - using green color
            AnimatedVisibility(
                visible = isRecording,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colorResource(id = R.color.green_color).copy(alpha = 0.15f))
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.green_color))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Recording... Speak clearly",
                        color = colorResource(R.color.brown),
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Gratitude note with green background hint
        Column {
            Text(
                text = "Gratitude Note",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(R.color.brown)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = gratitudeNote,
                onValueChange = { gratitudeNote = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = colorResource(id = R.color.green_color),
                    cursorColor = colorResource(R.color.brown),
                    focusedContainerColor = colorResource(id = R.color.green_color).copy(alpha = 0.05f)
                ),
                placeholder = { Text("Write one thing you're grateful for today...") },
                singleLine = true
            )
        }

        // Mood selection
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Tag,
                    contentDescription = null,
                    tint = colorResource(R.color.brown),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "How are you feeling?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.brown)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Mood tag chips in two rows for better organization
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // First row of mood tags
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MoodTag.values().take(4).forEach { mood ->
                        SelectableMoodChip(
                            mood = mood,
                            selected = selectedMood == mood,
                            onSelected = { selectedMood = mood },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                // Second row of mood tags
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MoodTag.values().drop(4).forEach { mood ->
                        SelectableMoodChip(
                            mood = mood,
                            selected = selectedMood == mood,
                            onSelected = { selectedMood = mood },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.LightGray.copy(alpha = 0.5f))
        Spacer(modifier = Modifier.height(16.dp))

        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Gray
                )
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (journalContent.isNotBlank()) {
                        onAddEntry(
                            journalContent,
                            if (gratitudeNote.isBlank()) "Nothing noted today" else gratitudeNote,
                            selectedMood
                        )
                    }
                },
                enabled = journalContent.isNotBlank(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.brown),
                    disabledContainerColor = colorResource(R.color.brown).copy(alpha = 0.5f)
                )
            ) {
                Text("Save Entry")
            }
        }
    }
}

@Composable
fun SelectableMoodChip(
    mood: MoodTag,
    selected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Get the appropriate color - using green_color resource for CALM mood
    val moodColor = when (mood) {
        MoodTag.CALM -> colorResource(id = R.color.green_color)
        else -> mood.color
    }

    val backgroundColor = if (selected) {
        moodColor.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = moodColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onSelected
            )
            .padding(vertical = 6.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mood.label,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) {
                if (mood == MoodTag.CALM) colorResource(id = R.color.brown) else moodColor
            } else Color.DarkGray,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ThoughtJournalScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    ThoughtJournalScreen(navController = navController)
}