package com.akashgupta.learningsqlite.room

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.room.Room
import com.akashgupta.learningsqlite.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class LearnRoomActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_room)

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Akash","2345678", Date(), 1))

        }

        database.contactDao().getContact().observe(this, Observer {
            Log.d("AKASH",it.toString())
        })

    }
}