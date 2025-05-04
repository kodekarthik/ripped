package com.yourname.ripped.ui.exercise

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourname.ripped.data.model.Exercise

@Composable
fun ExerciseListScreen(
    exercises: List<Exercise>,
    onAddExercise: () -> Unit,
    onExerciseClick: (Exercise) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Exercise Library") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddExercise) {
                Icon(Icons.Default.Add, contentDescription = "Add Exercise")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(exercises) { exercise ->
                ListItem(
                    text = { Text(exercise.name) },
                    secondaryText = { Text(exercise.focusAreas) },
                    modifier = Modifier.clickable { onExerciseClick(exercise) }
                )
                Divider()
            }
        }
    }
}