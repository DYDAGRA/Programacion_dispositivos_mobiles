package com.example.practica4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicia directamente el Formulario
        val intent = Intent(this, FormularioActivity::class.java)
        startActivity(intent)
        finish() // Cierra esta actividad para que no quede en el back stack
    }
}