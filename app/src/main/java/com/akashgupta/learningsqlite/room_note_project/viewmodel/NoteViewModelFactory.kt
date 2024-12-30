package com.akashgupta.learningsqlite.room_note_project.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akashgupta.learningsqlite.room_note_project.repository.NoteRepository

class NoteViewModelFactory(private val app: Application, private val noteRepository: NoteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }
}