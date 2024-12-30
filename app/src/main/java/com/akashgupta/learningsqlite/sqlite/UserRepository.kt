package com.akashgupta.learningsqlite.sqlite

import android.content.ContentValues

class UserRepository(private val dbHelper: DatabaseHelper) {

    fun insertUser(email: String, password: String): Boolean {
        val db = dbHelper.openConnection()
        return try {
            val contentValues = ContentValues().apply {
                put("email", email)
                put("password", password)
            }
            val result = db.insert("Users", null, contentValues)
            result != -1L
        } finally {
            dbHelper.closeConnection()
        }
    }

    fun getAllUsers(): List<Pair<String, String>> {
        val db = dbHelper.openConnection()
        val users = mutableListOf<Pair<String, String>>()
        return try {
            val cursor = db.rawQuery("SELECT email, password FROM Users", null)
            if (cursor.moveToFirst()) {
                do {
                    val email = cursor.getString(0)
                    val password = cursor.getString(1)
                    users.add(email to password)
                }while (cursor.moveToNext())
            }
            cursor.close()
            users
        } finally {
            dbHelper.closeConnection()
        }
    }

    fun updateUser(email: String, newPassword: String): Boolean{
        val db = dbHelper.openConnection()
        return try {
            val contentValues = ContentValues().apply {
                put("password",newPassword)
            }
            db.update("Users", contentValues, "email=?", arrayOf(email)) > 0
        } finally {
            dbHelper.closeConnection()
        }
    }

    fun deleteUser(email: String): Boolean{
        val db = dbHelper.openConnection()
        return try {
            db.delete("Users","email = ?", arrayOf(email)) > 0
        } finally {
            dbHelper.closeConnection()
        }
    }

}