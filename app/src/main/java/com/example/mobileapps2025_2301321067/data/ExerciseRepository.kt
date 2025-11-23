package com.example.mobileapps2025_2301321067.data

import androidx.lifecycle.LiveData
import com.example.mobileapps2025_2301321067.database.ExerciseDao

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    val readAllData: LiveData<List<Exercise>> = exerciseDao.readAllData()

    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.addExercise(exercise)
    }
}