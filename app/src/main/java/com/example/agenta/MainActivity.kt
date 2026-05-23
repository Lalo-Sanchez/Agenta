package com.example.agenta

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.agenta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        val prefs = getSharedPreferences("AgentaPrefs", Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            navGraph.setStartDestination(R.id.registerFragment)
            // Mark as not first run after this
            prefs.edit().putBoolean("isFirstRun", false).apply()
        } else {
            // Check if user is logged in (to be implemented)
            // For now, go to login
            navGraph.setStartDestination(R.id.loginFragment)
        }

        navController.graph = navGraph
    }
}