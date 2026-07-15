package com.example.restaurante.interfaz_grafica.cocina

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Estados.EstadoPedido
import com.example.restaurante.model.entities.PedidoEntity
import com.example.restaurante.repository.PedidoRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocinaScreen(

    pedidoRepository: PedidoRepository,

    onVolver: () -> Unit,

    onLogout: () -> Unit

) {

    val scope = rememberCoroutineScope()

    var pedidos by remember {

        mutableStateOf<List<PedidoEntity>>(emptyList())

    }

    LaunchedEffect(Unit) {

        pedidos = pedidoRepository.obtenerPedidos()

            .filter {

                it.estado == EstadoPedido.ENVIADO

            }

    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Pedidos en Cocina")

                },

                navigationIcon = {

                    TextButton(onClick = onVolver) {

                        Text("Volver")

                    }

                }

            )

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .padding(padding)
                .padding(16.dp)

        ) {

            items(pedidos) { pedido ->

                CocinaCard(

                    pedido,

                    onPreparado = {

                        scope.launch {

                            pedido.estado = EstadoPedido.PAGADO

                            pedidoRepository.actualizarPedido(pedido)

                            pedidos = pedidoRepository.obtenerPedidos()

                                .filter {

                                    it.estado == EstadoPedido.ENVIADO

                                }

                        }

                    }

                )

            }

        }

    }

}