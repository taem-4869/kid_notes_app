package com.taemallah.kidnotes.mainScreen.domain.repository

import com.taemallah.kidnotes.mainScreen.domain.modules.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {
    suspend fun upsert(note: Note)
    suspend fun delete(note: Note)
    fun getAllNotes() : Flow<List<Note>>
    fun getNotesCount() : Flow<Int>
    fun searchNote(query: String) : Flow<List<Note>>
}