package com.example.mynotes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mynotes.ui.components.NoteCard
import com.example.mynotes.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteHomeScreen(
    navController: NavController,
    viewModel: NoteViewModel = viewModel()
) {

    val notes = viewModel.notes

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("Notes")
                }
            )

        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("add")
                }
            ) {
                Text("+")
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