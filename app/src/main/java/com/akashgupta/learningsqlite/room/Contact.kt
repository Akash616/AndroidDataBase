package com.akashgupta.learningsqlite.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val phone: String,
    val createdDate: Date, //Convertors to convert into long.
    val isActive: Int
)
