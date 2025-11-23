package com.example.mobileapps2025_2301321067.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mobileapps2025_2301321067.data.Exercise
import com.example.mobileapps2025_2301321067.data.ExerciseRepository
import com.example.mobileapps2025_2301321067.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Exercise>>
    private val repository: ExerciseRepository

    init {
        val exerciseDao = AppDatabase.getDatabase(application).exerciseDao()
        repository = ExerciseRepository(exerciseDao)
        readAllData = repository.readAllData
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExercise(exercise)
        }
    }
}