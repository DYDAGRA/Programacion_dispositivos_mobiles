package com.example.restaurante.interfaz_grafica.administrador

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.entities.PedidoEntity
import com.example.restaurante.repository.PedidoRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentasScreen(

    pedidoRepository: PedidoRepository,

    onVolver: () -> Unit

) {

    val pedidos = remember {

        mutableStateListOf<PedidoEntity>()

    }

    LaunchedEffect(Unit) {

        pedidos.clear()

        pedidos.addAll(

            pedidoRepository.obtenerPedidos()

        )

    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Historial de Ventas")

                },

                navigationIcon = {

                    TextButton(

                        onClick = onVolver

                    ) {

                        Text("←")

                    }

                }

            )

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)

        ) {

            items(pedidos) { pedido ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)

                ) {

                    Column(

                        modifier = Modifier.padding(16.dp)

                    ) {

                        Text(
                            "Pedido #${pedido.id}"
                        )

                        Text(
                            "Mesa ${pedido.numeroMesa}"
                        )

                        Text(
                            "Estado: ${pedido.estado}"
                        )

                    }

                }

            }

        }

    }

}