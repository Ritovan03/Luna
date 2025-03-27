package com.example.mentalhealthapp.domain.Usecases

import com.example.mentalhealthapp.domain.model.ThoughtDump
import com.example.mentalhealthapp.domain.repository.ThoughtDumpRepo
import kotlinx.coroutines.flow.Flow

class GetThoughtDumpsUseCase(private val repo: ThoughtDumpRepo) {
    operator fun invoke(userId: String): Flow<List<ThoughtDump>> {
        return repo.getThoughtDumps(userId)
    }
}

class AddThoughtDumpUseCase(private val repo: ThoughtDumpRepo) {
    suspend operator fun invoke(userId: String, thoughtDump: ThoughtDump): Boolean {
        return repo.addThoughtDump(userId, thoughtDump)
    }
}


class UpdateThoughtDumpUseCase(private val repo: ThoughtDumpRepo) {
    suspend operator fun invoke(userId: String, thoughtDump: ThoughtDump): Boolean {
        return repo.updateThoughtDump(userId, thoughtDump)
    }
}


class DeleteThoughtDumpUseCase(private val repo: ThoughtDumpRepo) {
    suspend operator fun invoke(userId: String, thoughtDumpId: String): Boolean {
        return repo.deleteThoughtDump(userId, thoughtDumpId)
    }
}


