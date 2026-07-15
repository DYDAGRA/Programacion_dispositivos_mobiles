package com.example.restaurante.interfaz_grafica.administrador

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import com.example.restaurante.model.Productos.Producto
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductoDialog(

    producto: Producto,

    onDismiss: () -> Unit,

    onSave: (Producto) -> Unit

) {

    var nombre by remember {
        mutableStateOf(producto.nombre)
    }

    var precio by remember {
        mutableStateOf(producto.precio.toString())
    }

    var stock by remember {
        mutableStateOf(producto.stock.toString())
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("Editar Producto")
        },

        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )

                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") }
                )

                OutlinedTextField(
                    value = stock,
                    onValueChange = { stock = it },
                    label = { Text("Stock") }
                )

            }

        },

        confirmButton = {

            Button(

                onClick = {

                    onSave(

                        producto.copy(

                            nombre = nombre,

                            precio = precio.toDoubleOrNull() ?: 0.0,

                            stock = stock.toIntOrNull() ?: 0

                        )

                    )

                }

            ) {

                Text("Guardar")

            }

        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text("Cancelar")

            }

        }

    )

}