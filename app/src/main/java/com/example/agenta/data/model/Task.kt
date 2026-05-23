package com.example.agenta.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val subject: String,
    val taskDescription: String,
    val date: Long, // Use timestamp
    val isUrgent: Boolean = false,
    val isProject: Boolean = false,
    val isFavorite: Boolean = false,
    val isCompleted: Boolean = false
)