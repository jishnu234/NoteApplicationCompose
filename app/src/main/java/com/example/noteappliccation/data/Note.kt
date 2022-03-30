package com.example.noteappliccation.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

@RequiresApi(value = Build.VERSION_CODES.O)
data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryTime: LocalDateTime = LocalDateTime.now()
)
