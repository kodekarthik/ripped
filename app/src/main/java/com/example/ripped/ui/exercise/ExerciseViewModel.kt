package com.yourname.ripped.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.ripped.data.db.AppDatabase
import com.yourname.ripped.data.model.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel(private val db: AppDatabase) : ViewModel() {
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    fun loadExercises() {
        viewModelScope.launch {
            _exercises.value = db.exerciseDao().getAll()
        }
    }

    fun addExercise(exercise: Exercise, onDone: () -> Unit) {
        viewModelScope.launch {
            db.exerciseDao().insert(exercise)
            loadExercises()
            onDone()
        }
    }

    fun updateExercise(exercise: Exercise, onDone: () -> Unit) {
        viewModelScope.launch {
            db.exerciseDao().update(exercise)
            loadExercises()
            onDone()
        }
    }

    fun deleteExercise(exercise: Exercise, onDone: () -> Unit) {
        viewModelScope.launch {
            db.exerciseDao().delete(exercise)
            loadExercises()
            onDone()
        }
    }
}