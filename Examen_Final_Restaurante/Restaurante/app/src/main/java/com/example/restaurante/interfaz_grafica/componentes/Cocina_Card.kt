package com.example.restaurante.interfaz_grafica.cocina

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.entities.PedidoEntity

@Composable
fun CocinaCard(

    pedido: PedidoEntity,

    onPreparado: () -> Unit

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                "Mesa ${pedido.numeroMesa}",

                style = MaterialTheme.typography.titleLarge

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Pedido #${pedido.id}")

            Spacer(modifier = Modifier.height(12.dp))

            Button(

                onClick = onPreparado

            ) {

                Text("Marcar como preparado")

            }

        }

    }

}