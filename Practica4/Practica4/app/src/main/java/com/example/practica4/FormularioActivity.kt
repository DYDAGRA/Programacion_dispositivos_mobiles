package com.example.practica4
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etCiudad: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnContinuar: Button

    private val resumenLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "Perfil guardado correctamente", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etCiudad = findViewById(R.id.etCiudad)
        etCorreo = findViewById(R.id.etCorreo)
        btnContinuar = findViewById(R.id.btnContinuar)

        savedInstanceState?.let {
            etNombre.setText(it.getString("nombre"))
            etEdad.setText(it.getString("edad"))
            etCiudad.setText(it.getString("ciudad"))
            etCorreo.setText(it.getString("correo"))
        }

        btnContinuar.setOnClickListener {
            if (validarCampos()) {
                val usuario = Usuario(
                    nombre = etNombre.text.toString().trim(),
                    edad = etEdad.text.toString().toInt(),
                    ciudad = etCiudad.text.toString().trim(),
                    correo = etCorreo.text.toString().trim()
                )

                val intent = Intent(this, ResumenActivity::class.java).apply {
                    putExtra("usuario", usuario)
                }
                resumenLauncher.launch(intent)
            }
        }
    }

    private fun validarCampos(): Boolean {
        if (etNombre.text.isBlank() || etEdad.text.isBlank() ||
            etCiudad.text.isBlank() || etCorreo.text.isBlank()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nombre", etNombre.text.toString())
        outState.putString("edad", etEdad.text.toString())
        outState.putString("ciudad", etCiudad.text.toString())
        outState.putString("correo", etCorreo.text.toString())
    }
}