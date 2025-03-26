package com.example.mentalhealthapp.domain.repository

import com.example.mentalhealthapp.domain.model.Todo

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(userId: String): Flow<List<Todo>>
    suspend fun addTodo(userId: String, todo: Todo): Boolean
    suspend fun updateTodo(userId: String, todo: Todo): Boolean
    suspend fun deleteTodo(userId: String, todoId: String): Boolean
}
