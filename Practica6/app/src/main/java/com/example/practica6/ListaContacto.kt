package com.example.practica6

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListaScreen(navController: NavController) {

    var contactos by remember {
        mutableStateOf(
            listOf(
                Contacto(1, "Juan Pérez", "5512345678", true),
                Contacto(2, "María López", "5587654321", false),
                Contacto(3, "Carlos Ruiz", "5598765432", false)
            )
        )
    }

    val contactosOrdenados = contactos.sortedByDescending { it.favorito }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("formulario") }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar contacto")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                text = "Mis Contactos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {
                items(contactosOrdenados) { contacto ->
                    ContactoItem(
                        contacto = contacto,
                        onFavoritoClick = { id: Int ->
                            contactos = contactos.map {
                                if (it.id == id) it.copy(favorito = !it.favorito) else it
                            }
                        },
                        onDeleteClick = { id: Int ->
                            contactos = contactos.filter { it.id != id }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ContactoItem(
    contacto: Contacto,
    onFavoritoClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = contacto.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = contacto.telefono, style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = { onFavoritoClick(contacto.id) }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorito",
                    tint = if (contacto.favorito) Color.Yellow else Color.Gray
                )
            }

            IconButton(onClick = { onDeleteClick(contacto.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Red
                )
            }
        }
    }
}