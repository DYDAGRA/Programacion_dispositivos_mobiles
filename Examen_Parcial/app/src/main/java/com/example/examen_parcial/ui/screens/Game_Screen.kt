package com.example.examen_parcial.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examen_parcial.viewmodel.Modelo_Juego

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavController,
    viewModel: Modelo_Juego
) {
    val state by viewModel.gameState.collectAsState()

    val colors = listOf(
        Color.Red, Color.Green, Color.Blue,
        Color.Yellow, Color.Magenta
    )

    val colorNames = listOf("Rojo", "Verde", "Azul", "Amarillo", "Magenta")

    // Navegar automáticamente al terminar
    LaunchedEffect(state.isGameOver) {
        if (state.isGameOver) {
            navController.navigate("result/${state.score}") {
                popUpTo("game") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Juego de Colores") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Puntaje: ${state.score}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "⏱ ${state.timeLeft}s",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (state.timeLeft <= 10) Color.Red else Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Color objetivo
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.getOrElse(state.currentColor) { Color.Gray }),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = colorNames.getOrElse(state.currentColor) { "Error" },
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Feedback
            if (state.message.isNotEmpty()) {
                Text(
                    text = state.message,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (state.message.contains("Excelente")) Color.Green else Color.Red
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Botones
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                colors.chunked(2).forEach { rowColors ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowColors.forEach { color ->
                            val index = colors.indexOf(color)
                            Button(
                                onClick = { viewModel.onColorSelected(index) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(80.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = color),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = colorNames[index],
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}