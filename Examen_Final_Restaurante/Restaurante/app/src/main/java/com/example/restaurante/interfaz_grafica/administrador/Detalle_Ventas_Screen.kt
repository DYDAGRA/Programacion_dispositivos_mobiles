package com.example.restaurante.interfaz_grafica.administrador

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.database.relations.PedidoConItems
import com.example.restaurante.repository.PedidoRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleVentaScreen(

    pedidoId: Int,

    pedidoRepository: PedidoRepository,

    onVolver: () -> Unit

) {

    var detalle by remember {

        mutableStateOf<PedidoConItems?>(null)

    }

    LaunchedEffect(Unit) {

        detalle = pedidoRepository.obtenerPedidoCompleto(
            pedidoId
        )

    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Detalle de Venta")

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

        detalle?.let { pedido ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)

            ) {

                Text("Pedido #${pedido.pedido.id}")

                Text("Mesa ${pedido.pedido.numeroMesa}")

                Spacer(
                    Modifier.height(16.dp)
                )

                LazyColumn {

                    items(pedido.items) { item ->

                        Card(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)

                        ) {

                            Column(

                                modifier = Modifier.padding(12.dp)

                            ) {

                                Text("Producto ID: ${item.productoId}")

                                Text("Cantidad: ${item.cantidad}")

                                Text("Precio: S/. ${item.precioUnitario}")

                            }

                        }

                    }

                }

            }

        }

    }

}