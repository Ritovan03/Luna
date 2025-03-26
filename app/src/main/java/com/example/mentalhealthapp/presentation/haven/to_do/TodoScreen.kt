package com.example.mentalhealthapp.presentation.haven.to_do

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.rememberDismissState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.ui.theme.UrbanistFont
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlin.math.abs

enum class TaskPriority(val color: Color, val label: String) {
    HIGH(Color(0xFFE57373), "High"), // Red-ish
    MEDIUM(Color(0xFFFFD54F), "Medium"), // Yellow-ish
    LOW(Color(0xFF81C784), "Low") // Green-ish
}

enum class SortOption(val label: String) {
    DUE_DATE("Due Date"),
    PRIORITY("Priority")
}

data class Task(
    // Use UUID instead of System.currentTimeMillis() to ensure uniqueness
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val dueDate: Date?,
    val priority: TaskPriority,
    var isCompleted: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.offwhite_screen_color),
    ) { innerPadding ->
        ToDoContent(Modifier.padding(innerPadding),
            onBackButtonPressed = { navController.popBackStack() }
        )
    }
}

@Composable
fun AddTaskFAB(onAddTask: () -> Unit) {
    FloatingActionButton(
        onClick = onAddTask,
        containerColor = colorResource(R.color.brown),
        contentColor = Color.White,
        shape = CircleShape,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Task",
            modifier = Modifier.size(32.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ToDoContent(modifier: Modifier = Modifier, onBackButtonPressed: () -> Unit) {
    // Sample tasks for demonstration
    val tasks = remember {
        mutableStateListOf(
            Task(
                title = "Meditation practice",
                dueDate = Calendar.getInstance().apply {
                    add(Calendar.HOUR, 2)
                }.time,
                priority = TaskPriority.HIGH
            ),
            Task(
                title = "CBT journal entry",
                dueDate = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_MONTH, 1)
                }.time,
                priority = TaskPriority.MEDIUM
            ),
            Task(
                title = "Walking in nature",
                dueDate = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_MONTH, 3)
                }.time,
                priority = TaskPriority.LOW,
                isCompleted = true
            )
        )
    }

    var selectedSortOption by remember { mutableStateOf(SortOption.DUE_DATE) }
    var showAddTaskSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // Sort the tasks based on selection
    val sortedTasks = when (selectedSortOption) {
        SortOption.DUE_DATE -> tasks.sortedWith(compareBy({ !it.isCompleted }, { it.dueDate }))
        SortOption.PRIORITY -> tasks.sortedWith(compareBy({ !it.isCompleted }, { it.priority }))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Header and sort options
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
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
                text = "My Tasks",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.brown),
                fontFamily = UrbanistFont,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }

        // Sort options
        SortOptionsRow(
            selectedSortOption = selectedSortOption,
            onSortOptionSelected = { selectedSortOption = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Task list
        if (tasks.isEmpty()) {
            EmptyTasksState(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sortedTasks, key = { it.id }) { task ->
                    // Swipe to delete implementation
                    val dismissState = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            if (dismissValue == DismissValue.DismissedToStart) {
                                // Remove task when dismissed
                                tasks.removeIf { it.id == task.id }
                                true
                            } else {
                                false
                            }
                        }
                    )

                    SwipeToDeleteTaskItem(
                        task = task,
                        dismissState = dismissState,
                        onTaskChecked = { completed ->
                            val index = tasks.indexOfFirst { it.id == task.id }
                            if (index >= 0) {
                                tasks[index] = tasks[index].copy(isCompleted = completed)
                            }
                        }
                    )
                }

                // Extra space at bottom to avoid FAB overlap
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }

    // Add Task Bottom Sheet
    if (showAddTaskSheet) {
        ModalBottomSheet(
            onDismissRequest = { showAddTaskSheet = false },
            sheetState = sheetState
        ) {
            AddTaskContent(
                onAddTask = { title, date, priority ->
                    tasks.add(
                        Task(
                            title = title,
                            dueDate = date,
                            priority = priority
                        )
                    )
                    scope.launch {
                        sheetState.hide()
                        showAddTaskSheet = false
                    }
                },
                onDismiss = {
                    scope.launch {
                        sheetState.hide()
                        showAddTaskSheet = false
                    }
                }
            )
        }
    }

    // Floating action button click handler
    LaunchedEffect(true) {
        // This connection allows showing the sheet when the FAB is clicked
        // In a real implementation, you'd connect this to your viewModel
    }

    // Connect the FAB to show the add task sheet
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        AddTaskFAB {
            showAddTaskSheet = true
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDeleteTaskItem(
    task: Task,
    dismissState: DismissState,
    onTaskChecked: (Boolean) -> Unit
) {
    // Track the swipe progress to control background visibility and alignment
    val dismissDirection = dismissState.dismissDirection
    val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(0.4f) },
        background = {
            // This background will only be visible during swipe
            val color = Color(0xFFA375F8) // Light purple color

            // Calculate alpha based on swipe progress
            val swipeProgress = if (dismissDirection == DismissDirection.EndToStart) {
                abs(dismissState.progress.fraction)
            } else {
                0f
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd // Right-aligned for left swipe
            ) {
                Row(
                    modifier = Modifier.alpha(swipeProgress),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Delete",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Task",
                        tint = Color.White
                    )
                }
            }
        },
        dismissContent = {
            // The actual task item
            TaskItem(task, onTaskChecked)
        }
    )

    // Handle actual deletion
    LaunchedEffect(isDismissed) {
        if (isDismissed) {
            // This should be handled by the confirmStateChange callback passed to rememberDismissState
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortOptionsRow(
    selectedSortOption: SortOption,
    onSortOptionSelected: (SortOption) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Sort,
            contentDescription = "Sort",
            tint = colorResource(R.color.brown),
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = "Sort by:",
            color = colorResource(R.color.brown),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(end = 12.dp)
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            SortOption.values().forEach { option ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = option.ordinal,
                        count = SortOption.values().size
                    ),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = colorResource(R.color.brown),
                        activeContentColor = Color.White,
                        inactiveContainerColor = Color.Transparent,
                        inactiveContentColor = colorResource(R.color.brown)
                    ),
                    border = SegmentedButtonDefaults.borderStroke(
                        color = colorResource(R.color.brown)
                    ),
                    selected = selectedSortOption == option,
                    onClick = { onSortOptionSelected(option) }
                ) {
                    Text(option.label)
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskChecked: (Boolean) -> Unit
) {
    val alpha by animateFloatAsState(
        targetValue = if (task.isCompleted) 0.7f else 1f,
        label = "Task alpha"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox
            Icon(
                imageVector = if (task.isCompleted) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                contentDescription = if (task.isCompleted) "Mark incomplete" else "Mark complete",
                tint = if (task.isCompleted) colorResource(R.color.brown) else Color.Gray,
                modifier = Modifier
                    .size(28.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false,
                            radius = 14.dp
                        ),
                        onClick = { onTaskChecked(!task.isCompleted) }
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Task details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Title with strikethrough if completed
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (task.isCompleted) Color.Gray else Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Date and time
                task.dueDate?.let { date ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

                        Text(
                            text = dateFormat.format(date),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            imageVector = Icons.Outlined.Schedule,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = timeFormat.format(date),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            // Priority indicator
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(task.priority.color)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskContent(
    onAddTask: (String, Date?, TaskPriority) -> Unit,
    onDismiss: () -> Unit
) {
    var taskTitle by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf(TaskPriority.MEDIUM) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    var selectedDate by remember { mutableStateOf(calendar.time) }

    val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val timeFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Text(
            text = "Add New Task",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.brown),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Task title input
        OutlinedTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = { Text("Task title") },
            placeholder = { Text("Enter task title...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = colorResource(R.color.brown),
                focusedTextColor = colorResource(R.color.brown),
                unfocusedTextColor = Color.DarkGray
            )
        )

        // Date and time selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Date selector
            Button(
                onClick = { showDatePicker = true },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorResource(R.color.brown)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color.LightGray)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Select date",
                    tint = colorResource(R.color.brown)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = dateFormatter.format(selectedDate),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            // Time selector
            Button(
                onClick = { showTimePicker = true },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorResource(R.color.brown)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color.LightGray)
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Schedule,
                    contentDescription = "Select time",
                    tint = colorResource(R.color.brown)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = timeFormatter.format(selectedDate),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }

        // Priority selection
        Text(
            text = "Priority",
            fontWeight = FontWeight.Medium,
            color = colorResource(R.color.brown),
            modifier = Modifier.padding(top = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskPriority.values().forEach { priority ->
                PriorityChip(
                    priority = priority,
                    selected = selectedPriority == priority,
                    onSelected = { selectedPriority = priority },
                    modifier = Modifier.weight(1f)
                )
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

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (taskTitle.isNotBlank()) {
                        onAddTask(taskTitle, selectedDate, selectedPriority)
                    }
                },
                enabled = taskTitle.isNotBlank(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.brown),
                    disabledContainerColor = colorResource(R.color.brown).copy(alpha = 0.5f)
                )
            ) {
                Text("Save Task")
            }
        }
    }

    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate.time
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            val newCalendar = Calendar.getInstance().apply {
                                timeInMillis = it
                                set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
                                set(Calendar.MINUTE, calendar.get(Calendar.MINUTE))
                            }
                            selectedDate = newCalendar.time
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // Time Picker Dialog
    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = calendar.get(Calendar.HOUR_OF_DAY),
            initialMinute = calendar.get(Calendar.MINUTE)
        )

        Dialog(onDismissRequest = { showTimePicker = false }) {
            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header
                    Text(
                        text = "Select Time",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorResource(R.color.brown),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Don't constrain the TimePicker - let it use its natural size
                    TimePicker(
                        state = timePickerState,
                        colors = TimePickerDefaults.colors(
                            timeSelectorSelectedContainerColor = colorResource(R.color.brown),
                            timeSelectorSelectedContentColor = Color.White,
                            timeSelectorUnselectedContainerColor = Color.Transparent,
                            timeSelectorUnselectedContentColor = Color.DarkGray,
                            clockDialColor = colorResource(R.color.brown).copy(alpha = 0.1f),
                            clockDialSelectedContentColor = Color.White,
                            clockDialUnselectedContentColor = colorResource(R.color.brown),
                            periodSelectorBorderColor = colorResource(R.color.brown)
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { showTimePicker = false }
                        ) {
                            Text(
                                text = "Cancel",
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        TextButton(
                            onClick = {
                                val newCalendar = Calendar.getInstance().apply {
                                    time = selectedDate
                                    set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                    set(Calendar.MINUTE, timePickerState.minute)
                                }
                                selectedDate = newCalendar.time
                                showTimePicker = false
                            }
                        ) {
                            Text(
                                text = "OK",
                                color = colorResource(R.color.brown),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PriorityChip(
    priority: TaskPriority,
    selected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (selected) {
        priority.color.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = priority.color,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onSelected() }
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(priority.color)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = priority.label,
                fontSize = 14.sp,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (selected) priority.color else Color.DarkGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
fun EmptyTasksState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Placeholder for an illustration
        androidx.compose.foundation.Canvas(
            modifier = Modifier.size(100.dp)
        ) {
            val radius = size.minDimension / 3
            val brownColor = android.graphics.Color.parseColor("#795548")  // Brown color

            // Draw checklist icon
            drawCircle(
                color = Color(brownColor).copy(alpha = 0.1f),
                radius = radius
            )

            drawCircle(
                color = Color(brownColor),
                radius = radius,
                style = Stroke(width = 8f, cap = StrokeCap.Round)
            )

            // Draw checkmark
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(center.x - radius / 2, center.y)
                lineTo(center.x - radius / 6, center.y + radius / 2)
                lineTo(center.x + radius / 2, center.y - radius / 3)
            }

            drawPath(
                path = path,
                color = Color(brownColor),
                style = Stroke(width = 8f, cap = StrokeCap.Round)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "No tasks yet!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = colorResource(R.color.brown)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tap the + button to add your first task",
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@Preview
@Composable
fun ToDoScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    ToDoScreen(navController = navController)
}