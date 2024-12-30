package com.akashgupta.learningsqlite.room_note_project.repository

import com.akashgupta.learningsqlite.room_note_project.database.NoteDatabase
import com.akashgupta.learningsqlite.room_note_project.model.NoteEntityModel

class NoteRepository(private val db: NoteDatabase) {

    suspend fun insertNote(note: NoteEntityModel) = db.getNoteDao().insertNote(note)
    suspend fun updateNote(note: NoteEntityModel) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: NoteEntityModel) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)

}