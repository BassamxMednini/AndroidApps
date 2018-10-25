package com.example.bassam.notepad

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import com.example.bassam.notepad.data.Note
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            val noteText = editText.text.toString()
            if (noteText.isNotBlank()) {
                thread {
                    val note = Note(null, noteText)
                    db.noteDao().insert(note)
                }
                editText.setText("")

                // Schließt die Tastatur
                val view = this.currentFocus
                if (view != null) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getNotes().observe(this, Observer {
            if (it != null) {
                adapter.list.clear()
                adapter.list.addAll(it.map { it.text })
                adapter.notifyDataSetChanged()
            }
        })
    }
}
