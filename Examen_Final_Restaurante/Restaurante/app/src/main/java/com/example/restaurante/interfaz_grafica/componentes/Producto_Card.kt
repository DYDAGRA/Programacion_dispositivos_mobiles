package com.example.restaurante.interfaz_grafica.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Productos.Producto

@Composable
fun ProductoCard(

    producto: Producto,

    onEditar: () -> Unit,

    onEliminar: () -> Unit

) {

    ElevatedCard(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),

        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Text(

                    text = producto.nombre,

                    style = MaterialTheme.typography.titleMedium

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = producto.categoria.name,

                    style = MaterialTheme.typography.bodySmall,

                    color = Color.Gray

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = "S/. ${producto.precio}",

                    style = MaterialTheme.typography.bodyLarge

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = "Stock: ${producto.stock}",

                    style = MaterialTheme.typography.bodySmall,

                    color = Color(0xFF2E7D32)

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text =
                        if (producto.disponible)
                            "Disponible"
                        else
                            "No disponible",

                    color =
                        if (producto.disponible)
                            Color(0xFF388E3C)
                        else
                            Color.Red

                )

            }

            IconButton(onClick = onEditar) {

                Icon(

                    Icons.Default.Edit,

                    contentDescription = "Editar"

                )

            }

            IconButton(onClick = onEliminar) {

                Icon(

                    Icons.Default.Delete,

                    contentDescription = "Eliminar",

                    tint = Color.Red

                )

            }

        }

    }

}