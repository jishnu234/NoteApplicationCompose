package com.example.noteappliccation.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.noteappliccation.util.DateConverter
import com.example.noteappliccation.util.UUIDConverter

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun NoteDatabaseDao(): NoteDao
}