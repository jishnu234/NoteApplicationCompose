package com.example.noteappliccation.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.data.NoteDao
import com.example.noteappliccation.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun providesNotesDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.NoteDatabaseDao()

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase
    = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}