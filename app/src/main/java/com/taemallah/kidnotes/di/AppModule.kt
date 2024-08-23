package com.taemallah.kidnotes.di

import android.app.Application
import androidx.room.Room
import com.taemallah.kidnotes.constants.Const
import com.taemallah.kidnotes.mainScreen.data.database.NoteDao
import com.taemallah.kidnotes.mainScreen.data.database.NoteDatabase
import com.taemallah.kidnotes.mainScreen.data.repository.NoteRepoImpl
import com.taemallah.kidnotes.mainScreen.domain.repository.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDao(application: Application): NoteDao{
        return Room.databaseBuilder(
            application.applicationContext,
            NoteDatabase::class.java,
            Const.NOTE_DATABASE
        ).build().dao
    }

    @Provides
    @Singleton
    fun providesNoteRepo(noteDao: NoteDao): NoteRepo{
        return NoteRepoImpl(noteDao)
    }
}