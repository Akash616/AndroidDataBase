package com.akashgupta.learningsqlite.room

import androidx.room.TypeConverter
import java.util.Date

class Convertors {
    //Date, directly will not store in database.
    @TypeConverter
    fun fromDateToLong(value: Date): Long{
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date{
        return Date(value)
    }

}