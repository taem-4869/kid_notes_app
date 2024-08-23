package com.taemallah.kidnotes.mainScreen.data.repository

import android.util.Log
import com.taemallah.kidnotes.mainScreen.data.database.NoteDao
import com.taemallah.kidnotes.mainScreen.domain.modules.Note
import com.taemallah.kidnotes.mainScreen.domain.repository.NoteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepo {
    override suspend fun upsert(note: Note) {
        noteDao.upsertNote(note)
    }

    override suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        val result = noteDao.getAll()
        return result
    }

    override fun getNotesCount(): Flow<Int> {
        return noteDao.getNotesCount()
    }

    override fun searchNote(query: String): Flow<List<Note>> {
        return noteDao.search("%$query%")
    }
}