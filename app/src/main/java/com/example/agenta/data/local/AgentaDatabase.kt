package com.example.agenta.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agenta.data.model.Task
import com.example.agenta.data.model.User

@Database(entities = [User::class, Task::class], version = 1, exportSchema = false)
abstract class AgentaDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AgentaDatabase? = null

        fun getDatabase(context: Context): AgentaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AgentaDatabase::class.java,
                    "agenta_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}