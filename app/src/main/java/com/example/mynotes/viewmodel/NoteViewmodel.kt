package com.example.mynotes.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mynotes.model.Note

class NoteViewModel : ViewModel() {

    private val _notes = mutableStateListOf<Note>()

    val notes: List<Note>
        get() = _notes

    private var nextId = 0

    fun addNote(title: String, content: String) {

        val note = Note(
            id = nextId++,
            title = title,
            content = content,
            date = "Today"
        )

        _notes.add(note)
    }

    fun deleteNote(id: Int) {

        val index = _notes.indexOfFirst { it.id == id }

        if (index != -1) {
            _notes.removeAt(index)
        }
    }

    fun editNote(id: Int, newTitle: String, newContent: String) {

        val index = _notes.indexOfFirst { it.id == id }

        if (index != -1) {

            val updatedNote = _notes[index].copy(
                title = newTitle,
                content = newContent
            )

            _notes[index] = updatedNote
        }
    }
}