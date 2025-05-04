package com.yourname.ripped.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val description: String,
    val focusAreas: String,
    val type: String,
    val youtubeLink: String? = null
)