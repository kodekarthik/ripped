package com.yourname.ripped.ui.exercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourname.ripped.data.model.Exercise

@Composable
fun AddEditExerciseScreen(
    initialExercise: Exercise? = null,
    onSave: (Exercise) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(initialExercise?.name ?: "") }
    var description by remember { mutableStateOf(initialExercise?.description ?: "") }
    var focusAreas by remember { mutableStateOf(initialExercise?.focusAreas ?: "") }
    var type by remember { mutableStateOf(initialExercise?.type ?: "STRENGTH") }
    var youtubeLink by remember { mutableStateOf(initialExercise?.youtubeLink ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        OutlinedTextField(value = focusAreas, onValueChange = { focusAreas = it }, label = { Text("Focus Areas (comma separated)") })
        OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Type (STRENGTH or CARDIO)") })
        OutlinedTextField(value = youtubeLink, onValueChange = { youtubeLink = it }, label = { Text("YouTube Link") })

        Row {
            Button(
                onClick = {
                    onSave(
                        Exercise(
                            id = initialExercise?.id ?: 0L,
                            name = name,
                            description = description,
                            focusAreas = focusAreas,
                            type = type,
                            youtubeLink = youtubeLink.ifBlank { null }
                        )
                    )
                }
            ) { Text("Save") }
            Spacer(Modifier.width(8.dp))
            OutlinedButton(onClick = onCancel) { Text("Cancel") }
        }
    }
}