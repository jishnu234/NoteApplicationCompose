package com.example.noteappliccation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.model.NoteDataSource

@RequiresApi(Build.VERSION_CODES.O)
class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNotes(note: Note) {
        noteList.add(note)
    }

    fun removeNotes(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes() = noteList
}