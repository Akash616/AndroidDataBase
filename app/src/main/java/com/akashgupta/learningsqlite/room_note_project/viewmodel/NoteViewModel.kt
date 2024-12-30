package com.akashgupta.learningsqlite.room_note_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.akashgupta.learningsqlite.room_note_project.model.NoteEntityModel
import com.akashgupta.learningsqlite.room_note_project.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository): AndroidViewModel(app) {

    fun addNote(note: NoteEntityModel) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }

    fun updateNote(note: NoteEntityModel) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    fun deleteNote(note: NoteEntityModel) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query: String?) = noteRepository.searchNote(query)

}