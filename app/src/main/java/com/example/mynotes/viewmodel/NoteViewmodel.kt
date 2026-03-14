package com.example.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.database.NoteDatabase
import com.example.mynotes.model.Note
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = NoteDatabase.getDatabase(application).noteDao()

    val notes = dao.getAllNotes()

    fun addNote(title: String, content: String) {

        viewModelScope.launch {

            val note = Note(
                title = title,
                content = content,
                date = getCurrentDateTime()
            )

            dao.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {

        viewModelScope.launch {
            dao.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {

        viewModelScope.launch {
            dao.updateNote(note)
        }
    }

    private fun getCurrentDateTime(): String {

        val formatter = SimpleDateFormat(
            "dd MMM yyyy  HH:mm",
            Locale.getDefault()
        )

        return formatter.format(Date())
    }
}