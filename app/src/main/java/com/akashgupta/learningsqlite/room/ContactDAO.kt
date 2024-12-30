package com.akashgupta.learningsqlite.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getContact(): LiveData<List<Contact>>
    //get() operation use LiveData, Room check fun return type is LiveData, if yes
    //by default it execute on background thread.(no need to define suspend)

}