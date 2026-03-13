package com.example.mynotes.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.mynotes.ui.screens.NoteHomeScreen
import com.example.mynotes.ui.screens.AddEditScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynotes.viewmodel.NoteViewModel

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val viewModel: NoteViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            NoteHomeScreen(
                navController = navController,
                viewModel = viewModel
            )

        }

        composable("add") {

            AddEditScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )

        }

        composable("edit/{noteId}") { backStackEntry ->

            val noteId =
                backStackEntry.arguments
                    ?.getString("noteId")
                    ?.toInt()

            AddEditScreen(
                noteId = noteId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )

        }

    }

}