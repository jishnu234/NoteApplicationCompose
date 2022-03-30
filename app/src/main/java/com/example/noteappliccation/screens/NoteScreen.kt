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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappliccation.R
import com.example.noteappliccation.components.InputField
import com.example.noteappliccation.components.NoteButton
import com.example.noteappliccation.components.NoteCard
import com.example.noteappliccation.data.Note

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel,
    notes: MutableList<Note>,
    addNote: (Note) -> Unit,
    removeNote: (Note) -> Unit
) {
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
            label = "Title", value = noteViewModel.getTitleValue(),
            onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) noteViewModel.updateText(it.trim())

            })
        InputField(
            modifier = Modifier.fillMaxWidth(),
            label = "Note", value = noteViewModel.getDescriptionValue(),
            onTextChange = {
                if (it.all { char ->
                        char.isWhitespace() || char.isLetter()
                    }) noteViewModel.updateDescription(it.trim())
            })
        NoteButton(text = "ADD NOTES") {
            if (noteViewModel.getTitleValue().isNotEmpty() && noteViewModel.getDescriptionValue().isNotEmpty()) {
                addNote(Note(title = noteViewModel.getTitleValue(),
                    description = noteViewModel.getDescriptionValue()))
            }
            noteViewModel.updateText("")
            noteViewModel.updateDescription("")
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