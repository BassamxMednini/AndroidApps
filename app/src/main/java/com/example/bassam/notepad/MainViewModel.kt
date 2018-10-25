package com.example.bassam.notepad

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.bassam.notepad.data.Note

class MainViewModel : ViewModel() {
    private var notes: LiveData<List<Note>>? = null
    fun getNotes(): LiveData<List<Note>> {
        if (notes == null) {
            notes = db.noteDao().getAll()
        }
        return notes!!
    }
}