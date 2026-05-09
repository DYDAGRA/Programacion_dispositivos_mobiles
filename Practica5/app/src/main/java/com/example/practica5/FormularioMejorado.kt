package com.example.practica5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormularioMejorado() {
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    var genero by remember { mutableStateOf("Masculino") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var esActivo by remember { mutableStateOf(true) }
    var nivel by remember { mutableStateOf(5f) }

    var resultado by remember { mutableStateOf("") }
    var mostrarErrores by remember { mutableStateOf(false) }

    // Validaciones
    val nombreValido = nombre.isNotBlank()
    val edadValida = edad.toIntOrNull() != null && edad.toInt() in 1..120
    val correoValido = correo.contains("@") && correo.contains(".")
    val terminosValidos = aceptaTerminos

    val formularioValido = nombreValido && edadValida && correoValido && terminosValidos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Formulario Mejorado", fontSize = 24.sp, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre *") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarErrores && !nombreValido
        )
        if (mostrarErrores && !nombreValido) {
            Text("El nombre es obligatorio", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Edad
        OutlinedTextField(
            value = edad,
            onValueChange = { if (it.all { char -> char.isDigit() }) edad = it },
            label = { Text("Edad *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarErrores && !edadValida
        )
        if (mostrarErrores && !edadValida) {
            Text("Edad debe ser un número entre 1 y 120", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Correo
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarErrores && !correoValido
        )
        if (mostrarErrores && !correoValido) {
            Text("Ingrese un correo válido (debe contener @)", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Género - RadioButtons
        Text("Género", modifier = Modifier.align(Alignment.Start))
        Row {
            RadioButton(selected = genero == "Masculino", onClick = { genero = "Masculino" })
            Text("Masculino")
            RadioButton(selected = genero == "Femenino", onClick = { genero = "Femenino" })
            Text("Femenino")
            RadioButton(selected = genero == "Otro", onClick = { genero = "Otro" })
            Text("Otro")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Usuario activo")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = esActivo, onCheckedChange = { esActivo = it })
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Slider
        Text("Nivel de experiencia: ${nivel.toInt()}")
        Slider(
            value = nivel,
            onValueChange = { nivel = it },
            valueRange = 0f..10f,
            steps = 9
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = aceptaTerminos, onCheckedChange = { aceptaTerminos = it })
            Text("Acepto los términos y condiciones *")
        }
        if (mostrarErrores && !aceptaTerminos) {
            Text("Debes aceptar los términos", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    mostrarErrores = true
                    if (formularioValido) {
                        resultado = "Usuario $nombre, ${edad} años, $genero, " +
                                "${if (esActivo) "activo" else "inactivo"}, nivel ${nivel.toInt()}"
                    }
                },
                enabled = formularioValido
            ) {
                Text("Registrar")
            }

            Button(onClick = {
                // Limpiar todo
                nombre = ""
                edad = ""
                correo = ""
                genero = "Masculino"
                aceptaTerminos = false
                esActivo = true
                nivel = 5f
                resultado = ""
                mostrarErrores = false
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Limpiar")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Resultado
        if (resultado.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (formularioValido) Color(0xFFDCEDC8) else Color.LightGray
                )
            ) {
                Text(
                    text = resultado,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}