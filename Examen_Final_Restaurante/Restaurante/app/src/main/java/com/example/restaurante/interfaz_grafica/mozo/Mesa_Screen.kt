package com.example.restaurante.interfaz_grafica.mozo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Estados.EstadoMesa
import com.example.restaurante.model.Pedido.Mesa

@Composable
fun MesaCard(
    mesa: Mesa,
    onClick: () -> Unit
) {
    val color = when (mesa.estado) {
        EstadoMesa.LIBRE -> Color(0xFF4CAF50)
        EstadoMesa.OCUPADA -> Color(0xFFF44336)
        EstadoMesa.PAGANDO -> Color(0xFFFFC107)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.15f)),
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Mesa ${mesa.numero}", style = MaterialTheme.typography.titleLarge)
                Text(mesa.estado.name, style = MaterialTheme.typography.bodyMedium, color = color)

                mesa.pedidoActual?.let {
                    Text(
                        "${it.cantidadProductos()} items",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}