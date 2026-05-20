package com.example.examen_parcial.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_parcial.data.Caracteristicas_Juego
import com.example.examen_parcial.data.Historial_Juego
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Modelo_Juego : ViewModel() {

    private val _gameState = MutableStateFlow(Caracteristicas_Juego())
    val gameState = _gameState.asStateFlow()

    private val colors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta
    )

    private val colorNames = listOf("Rojo", "Verde", "Azul", "Amarillo", "Magenta")

    private var correctColorIndex = 0
    private val history = mutableListOf<Historial_Juego>()

    init {
        startNewGame()
    }

    fun startNewGame() {
        correctColorIndex = (colors.indices).random()
        _gameState.value = Caracteristicas_Juego(
            currentColor = correctColorIndex,
            score = 0,
            timeLeft = 30,
            isGameOver = false,
            message = ""
        )
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_gameState.value.timeLeft > 0 && !_gameState.value.isGameOver) {
                delay(1000L)
                val current = _gameState.value
                _gameState.value = current.copy(timeLeft = current.timeLeft - 1)
            }
            if (_gameState.value.timeLeft <= 0) {
                endGame()
            }
        }
    }

    fun onColorSelected(selectedIndex: Int) {
        val current = _gameState.value

        if (selectedIndex == correctColorIndex) {
            // Acierto
            val newScore = current.score + 1
            correctColorIndex = (colors.indices).random()

            _gameState.value = current.copy(
                score = newScore,
                currentColor = correctColorIndex,
                message = "¡Excelente! +1"
            )
        } else {
            // Error
            _gameState.value = current.copy(message = "¡Incorrecto!")
        }

        // Limpiar mensaje después de 800ms
        viewModelScope.launch {
            delay(800)
            if (_gameState.value.message.isNotEmpty()) {
                _gameState.value = _gameState.value.copy(message = "")
            }
        }
    }

    private fun endGame() {
        val current = _gameState.value
        val finalScore = current.score

        // Guardar en historial
        val date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        history.add(Historial_Juego(finalScore, date))

        _gameState.value = current.copy(
            isGameOver = true,
            highScore = maxOf(current.highScore, finalScore)
        )
    }

    fun getHistory(): List<Historial_Juego> = history
}