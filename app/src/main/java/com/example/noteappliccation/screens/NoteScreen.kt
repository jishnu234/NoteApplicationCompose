package com.example.noteappliccation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappliccation.R
import com.example.noteappliccation.components.InputField
import com.example.noteappliccation.components.NoteButton
import com.example.noteappliccation.components.NoteCard
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.model.NoteDataSource


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: MutableList<Note>,
    addNote: (Note) -> Unit,
    removeNote: (Note) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            backgroundColor = Color(0xFFAFAEAE),
            actions = {
                Icon(
                    Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon",
                    tint = Color(0xFF4E4D4D)
                )
            }
        )
        Spacer(modifier = Modifier.size(24.dp))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            label = "Title", value = text,
            onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) text = it.trim()

            })
        InputField(
            modifier = Modifier.fillMaxWidth(),
            label = "Note", value = description,
            onTextChange = {
                if (it.all { char ->
                        char.isWhitespace() || char.isLetter()
                    }) description = it.trim()
            })
        NoteButton(text = "Save") {
            if (text.isNotEmpty() && description.isNotEmpty()) {
                addNote(Note(title = text, description = description))
            }
            text = ""
            description = ""
        }
        Divider(modifier = Modifier.padding(top = 16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes) { note ->

                NoteCard(
                    note = note,
                    onNoteClicked = {
                        removeNote(it)
                    }
                )

            }
        }
    }
}