package com.example.noteappliccation.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.noteappliccation.data.Note

class NoteDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes(): MutableList<Note> {
        return mutableListOf(
            Note(title = "Movie1", description = "This is some sample Movies"),
            Note(title = "Movie2", description = "This is some sample Movies"),
            Note(title = "Movie3", description = "This is some sample Movies"),
            Note(title = "Movie4", description = "This is some sample Movies"),
            Note(title = "Movie5", description = "This is some sample Movies"),
            Note(title = "Movie6", description = "This is some sample Movies"),
        )
    }
}