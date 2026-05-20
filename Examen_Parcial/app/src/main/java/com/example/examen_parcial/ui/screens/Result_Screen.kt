package com.example.examen_parcial.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examen_parcial.viewmodel.Modelo_Juego

@Composable
fun ResultScreen(
    navController: NavController,
    finalScore: Int,
    viewModel: Modelo_Juego
) {
    val state by viewModel.gameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Juego Terminado!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Tu Puntaje",
            fontSize = 20.sp
        )

        Text(
            text = "$finalScore",
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (finalScore >= state.highScore) {
            Text(
                text = "¡Nuevo récord! 🏆",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        } else {
            Text(
                text = "Mejor puntaje: ${state.highScore}",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                viewModel.startNewGame()
                navController.navigate("game") {
                    popUpTo("welcome") { saveState = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("JUGAR DE NUEVO", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                navController.navigate("welcome") {
                    popUpTo("welcome") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Menú")
        }
    }
}