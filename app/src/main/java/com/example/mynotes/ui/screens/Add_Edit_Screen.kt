package com.example.mynotes.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotes.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(

    noteId: Int? = null,
    viewModel: NoteViewModel ,
    onBack: () -> Unit

) {

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    LaunchedEffect(noteId) {

        if (noteId != null) {

            val note = viewModel.notes.find { it.id == noteId }

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

            TextField(

                value = title,
                onValueChange = { title = it },

                label = { Text("Title") },

                modifier = Modifier.fillMaxWidth()

            )

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

                        onClick = {
                            onBack()
                        }

                    ) {
                        Text("Cancel")
                    }

                } else {

                    Button(

                        onClick = {

                            viewModel.editNote(noteId, title, content)
                            onBack()

                        }

                    ) {
                        Text("Update")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(

                        onClick = {

                            viewModel.deleteNote(noteId)
                            onBack()

                        }

                    ) {
                        Text("Delete")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedButton(

                        onClick = {
                            onBack()
                        }

                    ) {
                        Text("Cancel")
                    }

                }

            }

        }

    }

}