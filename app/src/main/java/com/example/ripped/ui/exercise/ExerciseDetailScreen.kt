package com.yourname.ripped.ui.exercise

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.yourname.ripped.data.model.Exercise

@Composable
fun ExerciseDetailScreen(
    exercise: Exercise,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        Text(exercise.name, style = MaterialTheme.typography.h5)
        Text("Focus Areas: ${exercise.focusAreas}")
        Text("Type: ${exercise.type}")
        Text(exercise.description)
        exercise.youtubeLink?.let { link ->
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
            }) {
                Text("Play YouTube Video")
            }
        }
        Row {
            OutlinedButton(onClick = onEdit) { Text("Edit") }
            Spacer(Modifier.width(8.dp))
            OutlinedButton(onClick = onDelete) { Text("Delete") }
        }
    }
}