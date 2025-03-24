package com.example.mentalhealthapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentalhealthapp.domain.Usecases.AddTodoUseCase
import com.example.mentalhealthapp.domain.Usecases.DeleteTodoUseCase
import com.example.mentalhealthapp.domain.Usecases.GetTodosUseCase
import com.example.mentalhealthapp.domain.Usecases.UpdateTodoUseCase
import com.example.mentalhealthapp.domain.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class ToDoViewModel(
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val getTodosUseCase : GetTodosUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {

    private val _todoState = MutableStateFlow<ResultState<List<Todo>>>(ResultState.Loading)
    val todoState: StateFlow<ResultState<List<Todo>>> = _todoState


    fun fetchTodos(userId: String) {
        viewModelScope.launch {
            getTodosUseCase(userId)
                .onStart { _todoState.value = ResultState.Loading }
                .catch { _todoState.value = ResultState.Error(it.message ?: "Unexpected Error") }
                .collect { _todoState.value = ResultState.Success(it) }
        }
    }

    fun addTodo(userId: String, todo: Todo) {
        viewModelScope.launch {
            val success = addTodoUseCase(userId, todo)
            if (success) fetchTodos(userId)
        }
    }

    fun updateTodo(userId: String, todo: Todo) {
        viewModelScope.launch {
            val success = updateTodoUseCase(userId, todo)
            if (success) fetchTodos(userId)
        }
    }

    fun deleteTodo(userId: String, todoId: String) {
        viewModelScope.launch {
            val success = deleteTodoUseCase(userId, todoId)
            if (success) fetchTodos(userId)
        }
    }
}

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}
