package com.example.agenta.data.local

import androidx.room.*
import com.example.agenta.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM tasks WHERE userId = :userId ORDER BY date ASC")
    fun getTasksByUser(userId: Int): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE userId = :userId AND date BETWEEN :start AND :end")
    fun getTasksByRange(userId: Int, start: Long, end: Long): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}