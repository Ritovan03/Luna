package com.example.mentalhealthapp.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mentalhealthapp.domain.model.Todo
@Composable
fun TodoListScreen(
    viewModel: ToDoViewModel,
) {
    val todoState by viewModel.todoState.collectAsState()
    var taskText by remember { mutableStateOf("") }
    var editingTodo by remember { mutableStateOf<Todo?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                modifier = Modifier.weight(1f),
                label = { Text(if (editingTodo != null) "Edit Task" else "Enter Task") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (taskText.isNotBlank()) {
                    if (editingTodo != null) {
                        val updatedTodo = editingTodo!!.copy(task = taskText)
                        viewModel.updateTodo(updatedTodo)
                        editingTodo = null
                    } else {
                        val newTodo = Todo(task = taskText)
                        viewModel.addTodo(newTodo)
                    }
                    taskText = ""
                }
            }) {
                Text(if (editingTodo != null) "Update" else "Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (todoState) {
            is ResultState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is ResultState.Error -> {
                val message = (todoState as ResultState.Error).message
                Text(
                    text = "Error: $message",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            is ResultState.Success -> {
                val todos = (todoState as ResultState.Success).data

                if (todos.isEmpty()) {
                    Text(
                        text = "No Todos present",
                        modifier = Modifier.padding(8.dp)
                    )
                } else {
                    LazyColumn {
                        items(todos) { todo ->
                            TodoItem(
                                todo = todo,
                                onDelete = { viewModel.deleteTodo(todo.id) },
                                onUpdate = {
                                    taskText = todo.task
                                    editingTodo = todo
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: () -> Unit,
    onUpdate: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(todo.task, modifier = Modifier.weight(1f))
            Row {
                IconButton(onClick = onUpdate) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}
