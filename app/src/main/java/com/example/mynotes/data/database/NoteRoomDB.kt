package com.example.mynotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.data.dao.NoteDao
import com.example.mynotes.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {

            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "notes_database"
                ).build()

            }

            return INSTANCE!!
        }
    }
}