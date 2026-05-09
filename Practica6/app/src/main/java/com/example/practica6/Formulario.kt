package com.example.practica6

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FormularioScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Nuevo Contacto", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            if (nombre.isNotBlank() && telefono.isNotBlank()) {
                navController.popBackStack()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Guardar")
        }

        Button(onClick = { navController.popBackStack() }, colors = ButtonDefaults.buttonColors(Color.Gray)) {
            Text("Cancelar")
        }
    }
}