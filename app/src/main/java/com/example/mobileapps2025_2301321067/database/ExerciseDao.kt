package com.example.mobileapps2025_2301321067.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobileapps2025_2301321067.data.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercise(exercise: Exercise)


    @Query("SELECT * FROM exercises_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Exercise>>

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}