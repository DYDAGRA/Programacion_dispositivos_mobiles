package com.example.practica4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResumenActivity : AppCompatActivity() {

    private lateinit var tvResumen: TextView
    private lateinit var btnConfirmar: Button
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        tvResumen = findViewById(R.id.tvResumen)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnEditar = findViewById(R.id.btnEditar)

        val usuario = intent.getSerializableExtra("usuario") as? Usuario

        usuario?.let {
            tvResumen.text = """
                📋 Resumen del Perfil
                
                Nombre: ${it.nombre}
                Edad: ${it.edad} años
                Ciudad: ${it.ciudad}
                Correo: ${it.correo}
            """.trimIndent()
        }

        btnConfirmar.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        btnEditar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}