package com.example.mentalhealthapp.domain.Usecases

import com.example.mentalhealthapp.domain.model.Todo
import com.example.mentalhealthapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class AddTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(userId: String, todo: Todo): Boolean {
        return repository.addTodo(userId, todo)
    }
}

class GetTodosUseCase(private val repository: TodoRepository) {
    operator fun invoke(userId: String): Flow<List<Todo>> {
        return repository.getTodos(userId)
    }
}

class DeleteTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(userId: String, todoId: String): Boolean {
        return repository.deleteTodo(userId, todoId)
    }
}

class UpdateTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(userId: String, todo: Todo): Boolean {
        return repository.updateTodo(userId, todo)
    }
}
