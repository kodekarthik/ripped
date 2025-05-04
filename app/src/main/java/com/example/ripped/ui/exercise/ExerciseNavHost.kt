package com.yourname.ripped.ui.exercise

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yourname.ripped.data.model.Exercise

@Composable
fun ExerciseNavHost(viewModel: ExerciseViewModel) {
    val navController = rememberNavController()
    val exercises by viewModel.exercises.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadExercises() }

    NavHost(navController, startDestination = "list") {
        composable("list") {
            ExerciseListScreen(
                exercises = exercises,
                onAddExercise = { navController.navigate("add") },
                onExerciseClick = { exercise -> navController.navigate("detail/${exercise.id}") }
            )
        }
        composable("add") {
            AddEditExerciseScreen(
                onSave = { exercise ->
                    viewModel.addExercise(exercise) { navController.popBackStack() }
                },
                onCancel = { navController.popBackStack() }
            )
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            val exercise = exercises.find { it.id == id }
            if (exercise != null) {
                ExerciseDetailScreen(
                    exercise = exercise,
                    onEdit = { navController.navigate("edit/${exercise.id}") },
                    onDelete = {
                        viewModel.deleteExercise(exercise) { navController.popBackStack() }
                    }
                )
            }
        }
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
            val exercise = exercises.find { it.id == id }
            if (exercise != null) {
                AddEditExerciseScreen(
                    initialExercise = exercise,
                    onSave = { updated ->
                        viewModel.updateExercise(updated) { navController.popBackStack() }
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}