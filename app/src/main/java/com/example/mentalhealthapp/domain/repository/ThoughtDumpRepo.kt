package com.example.mentalhealthapp.domain.repository

import com.example.mentalhealthapp.domain.model.ThoughtDump
import kotlinx.coroutines.flow.Flow

interface ThoughtDumpRepo {
    fun getThoughtDumps(userId: String): Flow<List<ThoughtDump>>
    suspend fun addThoughtDump(userId: String, thoughtDump: ThoughtDump): Boolean
    suspend fun updateThoughtDump(userId: String, thoughtDump: ThoughtDump): Boolean
    suspend fun deleteThoughtDump(userId: String, thoughtDumpId: String): Boolean
}