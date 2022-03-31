package com.example.noteappliccation.screens.notescreen.viewmodel

import android.icu.text.CaseMap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.model.NoteDataSource

@RequiresApi(Build.VERSION_CODES.O)
class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()
    private var title = mutableStateOf("")
    private var description = mutableStateOf("")

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

    fun updateText(title:  String) { this.title.value = title}

    fun updateDescription(description: String) { this.description.value = description }

    fun getTitleValue(): String = title.value

    fun getDescriptionValue(): String = description.value
}