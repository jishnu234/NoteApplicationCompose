package com.example.noteappliccation.repository

import com.example.noteappliccation.data.Note
import com.example.noteappliccation.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao){

    suspend fun addNote(note: Note) = noteDao.addNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun deleteAll() = noteDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes().flowOn(Dispatchers.IO).conflate()

}