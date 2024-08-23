package com.taemallah.kidnotes.mainScreen.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.taemallah.kidnotes.mainScreen.domain.modules.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getAll() : Flow<List<Note>>

    @Query("SELECT COUNT(*) FROM Note")
    fun getNotesCount() : Flow<Int>

    @Query("SELECT * FROM Note WHERE body LIKE :query ")
    fun search(query: String) : Flow<List<Note>>

}