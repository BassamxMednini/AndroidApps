package com.example.bassam.notepad.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDao {
    @Query("select * from note")
    fun getAll(): LiveData<List<Note>>

    @Insert
    fun insert(note: Note)
}