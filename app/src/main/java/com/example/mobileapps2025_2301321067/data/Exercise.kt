package com.example.mobileapps2025_2301321067.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "exercises_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val sets: Int,
    val reps: Int,
    val weight: Double,
    val notes: String = "",
    val date: Long = System.currentTimeMillis()
) : Serializable