package com.example.agenta.data.local

import com.example.agenta.data.model.User
import com.example.agenta.data.model.Task
import kotlinx.coroutines.flow.Flow

class AgentaRepository(private val userDao: UserDao, private val taskDao: TaskDao) {

    // User operations
    suspend fun registerUser(user: User): Long = userDao.registerUser(user)
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
    suspend fun getUserById(userId: Int): User? = userDao.getUserById(userId)

    // Task operations
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    fun getTasksByUser(userId: Int): Flow<List<Task>> = taskDao.getTasksByUser(userId)
    fun getTasksByRange(userId: Int, start: Long, end: Long): Flow<List<Task>> = 
        taskDao.getTasksByRange(userId, start, end)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}