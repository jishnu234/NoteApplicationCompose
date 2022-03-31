package com.example.noteappliccation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappliccation.data.Note
import com.example.noteappliccation.util.formatDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@ExperimentalComposeUiApi
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    label: String,
    imeAction: ImeAction = ImeAction.Next,
    inputType: KeyboardType = KeyboardType.Text,
    value: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier,
        label = { Text(text = label) },
        value = value,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = inputType
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        )
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = CircleShape,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 4.dp
    ),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        contentPadding = contentPadding,
        onClick = onClick,
        shape = shape
    ) {
        Text(text = text)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
//@Preview
@Composable
fun NoteCard(
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    topEnd = 18.dp, bottomStart = 18.dp
                )
            )
            .clickable {
                onNoteClicked(note)
            },
        shape = RoundedCornerShape(topEnd = 18.dp, bottomStart = 18.dp),
        color = Color(0xFFBFC8C9),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                modifier = Modifier.alpha(0.5f),
                text = note.description,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                //This will format the date and in user understandable format
                text = formatDate(note.entryTime.time),
                modifier = Modifier.alpha(0.5f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
        }
    }
}