package com.akashgupta.learningsqlite.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper private constructor(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDB"
        private const val DATABASE_VERSION = 2

        @Volatile
        private var instance: DatabaseHelper? = null

        //Purpose: This method ensures the DatabaseHelper instance is created only once and reused everywhere.
        fun getInstance(context: Context): DatabaseHelper {
            return instance ?: synchronized(this) {
                instance ?: DatabaseHelper(context.applicationContext).also { instance = it }
            }
        }
        /*
        How It Works ->
        First Check (instance ?:):
        The method first checks if the instance is already initialized.
        If instance is not null, it is returned immediately (no synchronization overhead).

        Synchronized Block:
        If instance is null, the synchronized(this) block ensures that only one thread at a time can execute the code inside it.
        This prevents multiple threads from creating separate instances simultaneously.

        Second Check (instance ?:):
        Inside the synchronized block, it checks instance again. This is necessary because another thread might have
        initialized the instance while the current thread was waiting for the lock.
        If instance is still null, it creates a new DatabaseHelper instance.

        also Block:
        The also block assigns the newly created DatabaseHelper to the instance variable for future use.
        */

        private const val CREATE_TABLE_USERS = """
            CREATE TABLE Users(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                email TEXT UNIQUE,
                password TEXT
            )
        """

        private const val CREATE_TABLE_ORDERS = """
            CREATE TABLE Orders(
                orderId INTEGER PRIMARY KEY AUTOINCREMENT,
                userId INTEGER,
                orderDetails TEXT,
                FOREIGN KEY (userId) REFERENCES Users (id)
            )
        """
    }

    private var database: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
        db?.execSQL(CREATE_TABLE_ORDERS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db == null) return

        when(oldVersion) {
            1 -> {
                db.execSQL("ALTER TABLE Users ADD COLUMN phone TEXT DEFAULT NULL")
            }
        }
    }

    @Synchronized
    fun openConnection(): SQLiteDatabase {
        if (database == null || !database!!.isOpen) {
            database = writableDatabase
        }
        return database!!
    }

    @Synchronized
    fun closeConnection() {
        if (database != null && database!!.isOpen) {
            database!!.close()
        }
    }
}