package com.example.mentalhealthapp.data.datasource

import android.util.Log
import com.example.mentalhealthapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ToDoDataSource {

    private val db = FirebaseFirestore.getInstance()

    fun getTodos(userId: String): Flow<List<Todo>> = flow {
        val ref = db.collection("users").document(userId).collection("todos")

        while (true) {
            try {
                val snapshot = ref.get().await()
                val todos = snapshot.documents.mapNotNull { it.toObject(Todo::class.java) }
                emit(todos)
            } catch (e: Exception) {
                emit(emptyList())
            }
            delay(5000)
        }
    }

    suspend fun addTodo(userId: String, todo: Todo): Boolean {
        return try {
            val userRef = db.collection("users").document(userId)
            Log.v("TAGG","Creating ref,${userRef}")
            userRef.set(mapOf("exists" to true), SetOptions.merge()).await()

            val todoRef = userRef.collection("todos").document()
            Log.v("TAGG","Creating ref done,${userRef}")
            val newTodo = todo.copy(id = todoRef.id)

            todoRef.set(newTodo).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateTodo(userId: String, todo: Todo): Boolean {
        return try {
            db.collection("users").document(userId).collection("todos").document(todo.id)
                .set(todo)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteTodo(userId: String, todoId: String): Boolean {
        return try {
            db.collection("users").document(userId).collection("todos").document(todoId)
                .delete()
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
