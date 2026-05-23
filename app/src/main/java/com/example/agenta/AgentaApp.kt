package com.example.agenta

import android.app.Application
import com.example.agenta.data.local.AgentaDatabase
import com.example.agenta.data.local.AgentaRepository

class AgentaApp : Application() {
    val database by lazy { AgentaDatabase.getDatabase(this) }
    val repository by lazy { AgentaRepository(database.userDao(), database.taskDao()) }
}