package com.example.mynotes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotes.model.Note
import com.example.mynotes.viewmodel.NoteViewModel
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(

    noteId: Int? = null,
    viewModel: NoteViewModel,
    onBack: () -> Unit

) {

    val notes by viewModel.notes.collectAsState(initial = emptyList())

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    LaunchedEffect(noteId, notes) {

        if (noteId != null) {

            val note = notes.find { it.id == noteId }

            note?.let {

                title = it.title
                content = it.content

            }

        }

    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    if (noteId == null)
                        Text("New Note")
                    else
                        Text("Edit Note")

                }

            )

        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()

        ) {

            Box {

                if (title.isEmpty()) {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Gray
                    )
                }

                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    textStyle = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),

                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),

                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(

                value = content,
                onValueChange = { content = it },

                label = { Text("Content") },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Row {

                if (noteId == null) {

                    Button(

                        onClick = {

                            viewModel.addNote(title, content)
                            onBack()

                        }

                    ) {
                        Text("Save")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedButton(

                        onClick = { onBack() }

                    ) {
                        Text("Cancel")
                    }

                } else {

                    Button(

                        onClick = {

                            val updatedNote = Note(
                                id = noteId,
                                title = title,
                                content = content,
                                date = "Today"
                            )

                            viewModel.updateNote(updatedNote)
                            onBack()

                        }

                    ) {
                        Text("Update")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(

                        onClick = {

                            val note = notes.find { it.id == noteId }

                            note?.let {
                                viewModel.deleteNote(it)
                            }

                            onBack()

                        }

                    ) {
                        Text("Delete")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedButton(

                        onClick = { onBack() }

                    ) {
                        Text("Cancel")
                    }

                }

            }

        }

    }

}