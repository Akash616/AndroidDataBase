package com.akashgupta.learningsqlite.room_note_project.user.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akashgupta.learningsqlite.R
import com.akashgupta.learningsqlite.room_note_project.database.NoteDatabase
import com.akashgupta.learningsqlite.room_note_project.repository.NoteRepository
import com.akashgupta.learningsqlite.room_note_project.viewmodel.NoteViewModel
import com.akashgupta.learningsqlite.room_note_project.viewmodel.NoteViewModelFactory

class RoomNoteActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_note)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }

}