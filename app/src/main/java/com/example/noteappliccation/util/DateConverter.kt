package com.example.noteappliccation.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date): Long = date.time

    @TypeConverter
    fun dateFromTimeStamp(timeStamp: Long) = Date(timeStamp)
}