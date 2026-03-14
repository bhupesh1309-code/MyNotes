package com.example.mynotes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mynotes.ui.components.NoteCard
import com.example.mynotes.viewmodel.NoteViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteHomeScreen(
    navController: NavController,
    viewModel: NoteViewModel = viewModel()
) {

    val notes by viewModel.notes.collectAsState(initial = emptyList())

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Notes") }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = { navController.navigate("add") },
                shape = CircleShape,
                containerColor = Color(0xFF81C784),
                contentColor = Color.White
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )

            }

        }

    ) { innerPadding ->

        LazyVerticalGrid(

            columns = GridCells.Fixed(2),

            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()

        ) {

            items(notes) { note ->

                NoteCard(

                    note = note,

                    onClick = {
                        navController.navigate("edit/${note.id}")
                    }

                )

            }

        }

    }

}