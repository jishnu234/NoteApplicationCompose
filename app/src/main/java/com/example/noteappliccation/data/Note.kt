package com.example.noteappliccation.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@RequiresApi(value = Build.VERSION_CODES.O)
//this annotation will change normal class to room database object
@Entity(tableName = "notes_table")// if we didn't provide the table name it will takes default table name as class name, ie Note
data class Note(
    // Refers that the id is going to be the primary key of our Table
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    //Refers that the column for notes title is "column_title"
    @ColumnInfo(name = "notes_title")
    val title: String,

    @ColumnInfo(name = "notes_description")
    val description: String,

    @ColumnInfo(name = "notes_entry_time")
    val entryTime: Date = Date.from(Instant.now())
)
