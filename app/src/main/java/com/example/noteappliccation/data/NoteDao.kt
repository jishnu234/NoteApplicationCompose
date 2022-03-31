package com.example.noteappliccation.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


/*
    Data access object(DAO) is the part of ROOM DB which
    connects directly into ROOM database
*/

@Dao
interface NoteDao {

    /*

     */
    @Query("Select * from notes_table")
    fun getAllNotes(): Flow<List<Note>>

    /*
        We know that this all functionalities runs on the main thread. So if the query takes
        more time to process or fetch data will blocks the main thread and the app become un-
        responsive. Avoid that kind of situation, we need to make these function suspendable.
        That is why we add a keyword suspend along with the functions
     */

    @Query("Select * from notes_table where id = :id")
    suspend fun getNotesById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("Delete from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

}