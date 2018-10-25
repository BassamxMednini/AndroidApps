package com.example.bassam.notepad.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}