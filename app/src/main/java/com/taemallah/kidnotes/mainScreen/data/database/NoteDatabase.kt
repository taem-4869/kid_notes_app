package com.taemallah.kidnotes.mainScreen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.taemallah.kidnotes.mainScreen.data.typeConverters.RoomConverters
import com.taemallah.kidnotes.mainScreen.domain.modules.Note

@Database(
    entities =[Note::class],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract val dao : NoteDao
}