package br.com.fiap.feedbackgames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    //TODO 1.0 - Incluir dependências
    //TODO 2.0 - Explicar dependencias
    //TODO 3.0 - Criar arquivo ApiService
    //TODO 3.1 - Criar interface FeedbackAPI
    //TODO 4.0 - Habilitar consumo de APIs HTTP no AndroidManifest
    //TODO 5.0 - Tentar rodar app (vai dar problema de permissão de acesso a internet)
    //TODO 6.0 - Incluir permissão de acesso a internet
    //TODO 7.0 - Tentar rodar app (Request HTTP Main Thread)
    //TODO 8.0 - Ir para slide e falar sobre Coroutines
    //TODO 9.0 - Tentar rodar app e consumir api (agora com sucesso)
}