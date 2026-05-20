package com.example.examen_parcial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examen_parcial.ui.screens.GameScreen
import com.example.examen_parcial.ui.screens.ResultScreen
import com.example.examen_parcial.ui.screens.WelcomeScreen
import com.example.examen_parcial.viewmodel.Modelo_Juego

@Composable
fun AppNavigation(
    viewModel: Modelo_Juego = Modelo_Juego()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }

        composable("game") {
            GameScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "result/{finalScore}",
            arguments = listOf(navArgument("finalScore") { type = NavType.IntType })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("finalScore") ?: 0
            ResultScreen(
                navController = navController,
                finalScore = score,
                viewModel = viewModel
            )
        }
    }
}