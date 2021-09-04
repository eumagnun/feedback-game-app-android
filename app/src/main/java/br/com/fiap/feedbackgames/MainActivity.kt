package br.com.fiap.feedbackgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import br.com.fiap.feedbackgames.network.FeedbackApi

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        loaddata()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun loaddata() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = FeedbackApi.retrofitService.getFeedbacks()
                println("retornoApi: Success: ${result.size} registros recuperados")
            } catch (e: Exception) {
                println("retornoApi: " + e.message)
            }
        }
    }
}