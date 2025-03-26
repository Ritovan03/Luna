package com.example.mentalhealthapp.domain.model

data class Todo(
    val id: String = "",
    val task: String = "",
    val priority: Priority = Priority.MEDIUM
)

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

