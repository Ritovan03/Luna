package com.example.mentalhealthapp.data.repository

import android.util.Log
import com.example.mentalhealthapp.data.datasource.ToDoDataSource
import com.example.mentalhealthapp.domain.model.Todo
import com.example.mentalhealthapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dataSource: ToDoDataSource
) : TodoRepository {

    override fun getTodos(userId: String): Flow<List<Todo>> {
        return dataSource.getTodos(userId)
    }

    override suspend fun addTodo(userId: String, todo: Todo): Boolean {
        Log.v("TAGYY","Add todo impl")
        return dataSource.addTodo(userId, todo)
    }

    override suspend fun updateTodo(userId: String, todo: Todo): Boolean {
        return dataSource.updateTodo(userId, todo)
    }

    override suspend fun deleteTodo(userId: String, todoId: String): Boolean {
        return dataSource.deleteTodo(userId, todoId)
    }
}