package com.example.bassam.notepad

import android.app.Application
import android.arch.persistence.room.Room
import com.example.bassam.notepad.data.MyDatabase

lateinit var db: MyDatabase

class App: Application() {
    override fun onCreate() {
        db = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "MyDatabase").build()
        super.onCreate()
    }
}