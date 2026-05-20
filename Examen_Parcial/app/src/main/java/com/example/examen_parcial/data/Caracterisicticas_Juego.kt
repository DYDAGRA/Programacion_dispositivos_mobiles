package com.example.examen_parcial.data

data class Caracteristicas_Juego(
    val currentColor: Int = 0,
    val score: Int = 0,
    val timeLeft: Int = 30,
    val isGameOver: Boolean = false,
    val message: String = "",
    val highScore: Int = 0
)