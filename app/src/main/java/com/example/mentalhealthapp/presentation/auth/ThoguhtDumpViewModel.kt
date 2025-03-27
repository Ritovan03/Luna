package com.example.mentalhealthapp.presentation.auth

import androidx.lifecycle.ViewModel
import com.example.mentalhealthapp.domain.Usecases.AddThoughtDumpUseCase
import com.example.mentalhealthapp.domain.Usecases.DeleteThoughtDumpUseCase
import com.example.mentalhealthapp.domain.Usecases.GetThoughtDumpsUseCase
import com.example.mentalhealthapp.domain.Usecases.UpdateThoughtDumpUseCase
import com.example.mentalhealthapp.domain.model.ThoughtDump
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ThoughtDumpViewModel @Inject constructor(
    private val getThoughtDumps: GetThoughtDumpsUseCase,
    private val addThoughtDump: AddThoughtDumpUseCase,
    private val updateThoughtDump: UpdateThoughtDumpUseCase,
    private val deleteThoughtDump: DeleteThoughtDumpUseCase
) : ViewModel() {

    fun fetchThoughtDumps(userId: String): Flow<List<ThoughtDump>> {
        return getThoughtDumps(userId)
    }

    suspend fun addThought(userId: String, thoughtDump: ThoughtDump): Boolean {
        return addThoughtDump(userId, thoughtDump)
    }

    suspend fun updateThought(userId: String, thoughtDump: ThoughtDump): Boolean {
        return updateThoughtDump(userId, thoughtDump)
    }

    suspend fun deleteThought(userId: String, thoughtDumpId: String): Boolean {
        return deleteThoughtDump(userId, thoughtDumpId)
    }
}
