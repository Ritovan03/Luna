package com.example.mentalhealthapp.data.datasource

import android.util.Log
import com.example.mentalhealthapp.domain.model.ThoughtDump
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await



class ThoughtDumpDataSource {

    private val db = FirebaseFirestore.getInstance()

    fun getThoughtDumps(userId: String): Flow<List<ThoughtDump>> = flow {
        val ref = db.collection("users").document(userId).collection("thoughtDumps")

        while (true) {
            try {
                val snapshot = ref.get().await()
                val thoughtDumps = snapshot.documents.mapNotNull { it.toObject(ThoughtDump::class.java) }
                emit(thoughtDumps)
            } catch (e: Exception) {
                emit(emptyList())
            }
            delay(5000)
        }
    }

    suspend fun addThoughtDump(userId: String, thoughtDump: ThoughtDump): Boolean {
        return try {
            val userRef = db.collection("users").document(userId)
            userRef.set(mapOf("exists" to true), SetOptions.merge()).await()

            val thoughtDumpRef = userRef.collection("thoughtDumps").document()
            val newThoughtDump = thoughtDump.copy(id = thoughtDumpRef.id)

            thoughtDumpRef.set(newThoughtDump).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateThoughtDump(userId: String, thoughtDump: ThoughtDump): Boolean {
        return try {
            db.collection("users").document(userId).collection("thoughtDumps").document(thoughtDump.id)
                .set(thoughtDump)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteThoughtDump(userId: String, thoughtDumpId: String): Boolean {
        return try {
            db.collection("users").document(userId).collection("thoughtDumps").document(thoughtDumpId)
                .delete()
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
