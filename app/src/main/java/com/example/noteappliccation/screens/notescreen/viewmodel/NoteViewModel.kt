package com.example.noteappliccation.screens.notescreen.viewmodel

import android.icu.text.CaseMap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.model.NoteDataSource
import com.example.noteappliccation.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject() constructor(val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private var title = mutableStateOf("")
    private var description = mutableStateOf("")

    init {
        viewModelScope.launch {
            repository.getAllNotes().distinctUntilChanged()
                .collect() { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty List ")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

     fun addNotes(note: Note) = viewModelScope.launch { repository.addNote(note) }
     fun removeNotes(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
     fun getAllNotes() = noteList

    fun updateText(title:  String) { this.title.value = title}
    fun updateDescription(description: String) { this.description.value = description }
    fun getTitleValue(): String = title.value
    fun getDescriptionValue(): String = description.value
}