package com.example.noteappliccation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.screens.NoteScreen
import com.example.noteappliccation.screens.NoteViewModel
import com.example.noteappliccation.ui.theme.NoteAppliccationTheme


@RequiresApi(Build.VERSION_CODES.O)
val notesViewModel = NoteViewModel()

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppliccationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NotesApp(notesViewModel)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val noteList = noteViewModel.getAllNotes()

    NoteScreen(
        noteViewModel = noteViewModel,
        notes = noteList,
        addNote = {
            noteViewModel.addNotes(it)
        },
        removeNote = {
            noteViewModel.removeNotes(it)
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppliccationTheme {
        NotesApp()
    }
}