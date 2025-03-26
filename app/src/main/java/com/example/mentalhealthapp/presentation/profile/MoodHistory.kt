package com.example.mentalhealthapp.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mentalhealthapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// Simple data class to represent a mood entry that works on all Android versions
data class MoodEntry(
    val date: Date,
    val mood: Mood
)

enum class Mood(val color: Color, val label: String) {
    HAPPY(Color(0xFF78C091), "Happy"),
    CALM(Color(0xFF96BAFF), "Calm"),
    NEUTRAL(Color(0xFFE0E0E0), "Neutral"),
    ANXIOUS(Color(0xFFFFD166), "Anxious"),
    SAD(Color(0xFF9FB5DF), "Sad"),
    ANGRY(Color(0xFFEF767A), "Angry")
}

// Simple class to represent a month that works on all Android versions
class MonthData(year: Int, month: Int) {
    private val calendar = Calendar.getInstance()

    init {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
    }

    fun getMonthName(): String {
        val format = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        return format.format(calendar.time)
    }

    fun getDaysInMonth(): Int {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getFirstDayOfWeek(): Int {
        return (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY) % 7
    }

    fun getDate(day: Int): Date {
        val newCal = Calendar.getInstance()
        newCal.time = calendar.time
        newCal.set(Calendar.DAY_OF_MONTH, day)
        return newCal.time
    }

    fun nextMonth(): MonthData {
        val newCal = Calendar.getInstance()
        newCal.time = calendar.time
        newCal.add(Calendar.MONTH, 1)
        return MonthData(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH))
    }

    fun previousMonth(): MonthData {
        val newCal = Calendar.getInstance()
        newCal.time = calendar.time
        newCal.add(Calendar.MONTH, -1)
        return MonthData(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH))
    }
}

@Composable
fun MoodHistoryCard() {
    var showMoodHistoryDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(22.dp),
                spotColor = colorResource(R.color.brown).copy(alpha = 0.15f)
            ),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.community_screen_color)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.mood_history_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )


            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Header with enhanced styling
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    // Custom icon with background
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(colorResource(R.color.brown).copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Mood History",
                            tint = colorResource(R.color.brown),
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Mood History",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.brown)
                        )
                        // Subtitle for added depth
                        Text(
                            text = "Track your journey",
                            fontSize = 14.sp,
                            color = colorResource(R.color.brown).copy(alpha = 0.7f),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Card content with enhanced typography
                Text(
                    text = "View your past mood entries and track your emotional patterns over time.",
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Stats preview - adds visual interest
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    // Mood stats indicators
                    MoodStatIndicator(
                        modifier = Modifier.weight(1f),
                        title = "Happy",
                        percentage = 65,
                        color = Color(0xFF49AD6C)
                    )
                    MoodStatIndicator(
                        modifier = Modifier.weight(1f),
                        title = "Calm",
                        percentage = 25,
                        color = Color(0xFF9A1752)
                    )
                    MoodStatIndicator(
                        modifier = Modifier.weight(1f),
                        title = "Other",
                        percentage = 10,
                        color = Color(0xFF3E69B0)
                    )
                }

                // Button with enhanced styling but same color
                OutlinedButton(
                    onClick = { showMoodHistoryDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(27.dp),
                    border = BorderStroke(1.5.dp, colorResource(R.color.brown)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = colorResource(R.color.brown),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "View Mood History",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.3.sp,
                            color = colorResource(R.color.brown)
                        )
                    }
                }
            }
        }
    }

    // Show the mood history dialog when requested
    if (showMoodHistoryDialog) {
        MoodHistoryDialog(
            onDismiss = { showMoodHistoryDialog = false }
        )
    }
}

@Composable
private fun MoodStatIndicator(
    modifier: Modifier = Modifier,
    title: String,
    percentage: Int,
    color: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((40.dp * percentage / 100))
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "$percentage%",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}
@Composable
fun MoodHistoryDialog(
    onDismiss: () -> Unit
) {
    // Get current date for "today" highlighting
    val today = Calendar.getInstance().time

    // Generate sample mood data using regular Date objects
    val cal = Calendar.getInstance()
    val moodEntries = remember {
        listOf(
            MoodEntry(addDays(cal, -1), Mood.HAPPY),
            MoodEntry(addDays(cal, -3), Mood.CALM),
            MoodEntry(addDays(cal, -5), Mood.ANXIOUS),
            MoodEntry(addDays(cal, -7), Mood.SAD),
            MoodEntry(addDays(cal, -10), Mood.ANGRY),
            MoodEntry(addDays(cal, -12), Mood.NEUTRAL)
        )
    }

    // Create our month data
    val calendar = Calendar.getInstance()
    var currentMonth by remember {
        mutableStateOf(MonthData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)))
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .width(320.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Header with month navigation and close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { currentMonth = currentMonth.previousMonth() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Previous Month",
                            tint = colorResource(R.color.brown)
                        )
                    }

                    Text(
                        text = currentMonth.getMonthName(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorResource(R.color.brown)
                    )

                    Row {
                        IconButton(
                            onClick = { currentMonth = currentMonth.nextMonth() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Next Month",
                                tint = colorResource(R.color.brown)
                            )
                        }

                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = colorResource(R.color.brown)
                            )
                        }
                    }
                }

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.LightGray
                )

                // Weekday headers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val daysOfWeek = listOf("S", "M", "T", "W", "T", "F", "S")
                    daysOfWeek.forEach { day ->
                        Text(
                            text = day,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Calendar Grid - FIXED: Proper height constraint
                val rows = ((currentMonth.getFirstDayOfWeek() + currentMonth.getDaysInMonth()) + 6) / 7
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((rows * 40).dp)
                ) {
                    DaysGrid(
                        monthData = currentMonth,
                        moodEntries = moodEntries,
                        today = today
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Mood legend - FIXED: Use a Row layout for each line instead of LazyVerticalGrid
                Text(
                    text = "Mood Legend",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(R.color.brown),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // First row of mood legend
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Mood.values().take(3).forEach { mood ->
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(mood.color, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = mood.label,
                                fontSize = 12.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }

                // Second row of mood legend
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Mood.values().drop(3).forEach { mood ->
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(mood.color, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = mood.label,
                                fontSize = 12.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}

// FIXED: Replaced LazyVerticalGrid with a simpler Row-based grid implementation
@Composable
fun DaysGrid(
    monthData: MonthData,
    moodEntries: List<MoodEntry>,
    today: Date
) {
    val firstDayOfWeek = monthData.getFirstDayOfWeek()
    val daysInMonth = monthData.getDaysInMonth()
    val rows = (firstDayOfWeek + daysInMonth + 6) / 7

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Create calendar rows
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Create 7 days per row (Sunday-Saturday)
                for (col in 0 until 7) {
                    val dayIndex = row * 7 + col
                    val dayOfMonth = dayIndex - firstDayOfWeek + 1

                    if (dayOfMonth in 1..daysInMonth) {
                        // Valid day in month
                        val date = monthData.getDate(dayOfMonth)
                        val mood = moodEntries.find { isSameDay(it.date, date) }?.mood
                        val isToday = isSameDay(date, today)

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(
                                    when {
                                        isToday -> colorResource(R.color.brown).copy(alpha = 0.3f)
                                        mood != null -> mood.color
                                        else -> Color.Transparent
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = dayOfMonth.toString(),
                                fontSize = 14.sp,
                                color = if (isToday) colorResource(R.color.brown) else Color.DarkGray,
                                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    } else {
                        // Empty space
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f))
                    }
                }
            }
        }
    }
}

// Helper function to add/subtract days from a Date
private fun addDays(calendar: Calendar, days: Int): Date {
    val newCal = Calendar.getInstance()
    newCal.time = calendar.time
    newCal.add(Calendar.DAY_OF_MONTH, days)
    return newCal.time
}

// Helper function to check if two dates are the same day
private fun isSameDay(date1: Date, date2: Date): Boolean {
    val cal1 = Calendar.getInstance()
    cal1.time = date1
    val cal2 = Calendar.getInstance()
    cal2.time = date2
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
            cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
}

@Preview
@Composable
fun MoodHistoryCardPreview() {
    MoodHistoryCard()
}