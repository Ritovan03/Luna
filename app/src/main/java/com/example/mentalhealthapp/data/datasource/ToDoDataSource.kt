package com.example.mentalhealthapp.data.datasource

import com.example.mentalhealthapp.domain.model.Todo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ToDoDataSource {

    private val dbRef = FirebaseDatabase.getInstance().getReference("users")

    fun getTodos(userId: String): Flow<List<Todo>> = callbackFlow {
        val ref = dbRef.child(userId).child("todos")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val todos = mutableListOf<Todo>()
                for (child in snapshot.children) {
                    val todo = child.getValue(Todo::class.java)
                    todo?.let { todos.add(it) }
                }
                trySend(todos).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    suspend fun addTodo(userId: String, todo: Todo): Boolean = suspendCancellableCoroutine { cont ->
        val key = dbRef.child(userId).child("todos").push().key
        if (key == null) cont.resume(false) else {
            val newTodo = todo.copy(id = key)
            dbRef.child(userId).child("todos").child(key).setValue(newTodo)
                .addOnCompleteListener { cont.resume(it.isSuccessful) }
        }
    }

    suspend fun updateTodo(userId: String, todo: Todo): Boolean = suspendCancellableCoroutine { cont ->
        dbRef.child(userId).child("todos").child(todo.id)
            .setValue(todo)
            .addOnCompleteListener { cont.resume(it.isSuccessful) }
    }

    suspend fun deleteTodo(userId: String, todoId: String): Boolean = suspendCancellableCoroutine { cont ->
        dbRef.child(userId).child("todos").child(todoId)
            .removeValue()
            .addOnCompleteListener { cont.resume(it.isSuccessful) }
    }

}
