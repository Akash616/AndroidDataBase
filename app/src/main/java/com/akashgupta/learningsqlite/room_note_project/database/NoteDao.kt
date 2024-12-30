package com.akashgupta.learningsqlite.room_note_project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akashgupta.learningsqlite.room_note_project.model.NoteEntityModel

@Dao
interface NoteDao {

    /*If SAME primary key is already exists, then old data replace with new data.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntityModel: NoteEntityModel)

    @Update
    suspend fun updateNote(noteEntityModel: NoteEntityModel)

    @Delete
    suspend fun deleteNote(noteEntityModel: NoteEntityModel)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteEntityModel>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<NoteEntityModel>>
}