package com.example.mentalhealthapp.data.repository

import com.example.mentalhealthapp.data.datasource.ThoughtDumpDataSource
import com.example.mentalhealthapp.domain.model.ThoughtDump
import com.example.mentalhealthapp.domain.repository.ThoughtDumpRepo
import kotlinx.coroutines.flow.Flow

class ThoughtRepoImp(private val dataSource: ThoughtDumpDataSource) : ThoughtDumpRepo {

    override fun getThoughtDumps(userId: String): Flow<List<ThoughtDump>> {
        return dataSource.getThoughtDumps(userId)
    }

    override suspend fun addThoughtDump(userId: String, thoughtDump: ThoughtDump): Boolean {
        return dataSource.addThoughtDump(userId, thoughtDump)
    }

    override suspend fun updateThoughtDump(userId: String, thoughtDump: ThoughtDump): Boolean {
        return dataSource.updateThoughtDump(userId, thoughtDump)
    }

    override suspend fun deleteThoughtDump(userId: String, thoughtDumpId: String): Boolean {
        return dataSource.deleteThoughtDump(userId, thoughtDumpId)
    }
}
