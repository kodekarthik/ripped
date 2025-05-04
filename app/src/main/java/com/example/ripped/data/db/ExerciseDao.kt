package com.yourname.ripped.data.db

import androidx.room.*
import com.yourname.ripped.data.model.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    suspend fun getAll(): List<Exercise>

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getById(id: Long): Exercise?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise): Long

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)
}