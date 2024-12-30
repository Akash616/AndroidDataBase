package com.akashgupta.learningsqlite.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.akashgupta.learningsqlite.R

class MainActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper.getInstance(this)
        userRepository = UserRepository(dbHelper)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val outputTextView: TextView = findViewById(R.id.outputTextView)

        // Save button
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val isSaved = userRepository.insertUser(email, password)
            outputTextView.text = if (isSaved) "User Saved!" else "Error Saving User"
        }

        // Print All button
        findViewById<Button>(R.id.printButton).setOnClickListener {
            val users = userRepository.getAllUsers()
            outputTextView.text = if (users.isNotEmpty()) {
                users.joinToString("\n") { "${it.first}: ${it.second}" }
            } else {
                "No users found"
            }
        }

        // Update button
        findViewById<Button>(R.id.updateButton).setOnClickListener {
            val email = emailEditText.text.toString()
            val newPassword = passwordEditText.text.toString()
            val isUpdated = userRepository.updateUser(email, newPassword)
            outputTextView.text = if (isUpdated) "User Updated!" else "Error Updating User"
        }

        // Delete button
        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            val email = emailEditText.text.toString()
            val isDeleted = userRepository.deleteUser(email)
            outputTextView.text = if (isDeleted) "User Deleted!" else "Error Deleting User"
        }

    }
}