package com.akashgupta.learningsqlite.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertors::class)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    //Singleton
    //Thread Safe
    companion object {

        /*@Volatile- Jasa hi humara INSTANCE variable mai kuch bhio write hota hai, matlab kuch bhi
        value assign hoti hai, toh wo sara thread ko available ho jati hai UPDATED VALUE. */
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase{
            if (INSTANCE == null){
                //Possibility - 2 or more thread can create database object at same time(multiple instance)
                //Solution ->locking mechanism to create one instance.
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ContactDatabase::class.java,
                        "contactDB")
                        .addMigrations(migration_1_2)
                        .build()
                }
            }
            return INSTANCE!!
        }

        //-------Migration-----------------------------------------------------------------------------
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }
        }

    }

}