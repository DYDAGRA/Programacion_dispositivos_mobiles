package com.example.restaurante.interfaz_grafica.mozo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Pedido.Mesa
import com.example.restaurante.model.Usuarios.Usuario
import com.example.restaurante.repository.ProductoRepository
import com.example.restaurante.service.PedidoService
import com.example.restaurante.repository.PedidoRepository
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeseroScreen(
    usuarioLogueado: Usuario,
    productoRepository: ProductoRepository,
    pedidoRepository: PedidoRepository,
    onLogout: () -> Unit
) {
    val pedidoService = remember { PedidoService() }
    val mesas = remember { mutableStateListOf<Mesa>() }

    LaunchedEffect(Unit) {
        if (mesas.isEmpty()) {
            for (i in 1..12) {
                mesas.add(Mesa(numero = i))
            }
        }
    }

    var mostrarComanda by remember { mutableStateOf(false) }
    var pedidoActual by remember { mutableStateOf<Mesa?>(null) }
    var mostrarPedidos by remember {
        mutableStateOf(false)
    }
    var mostrarPreCuenta by remember {

        mutableStateOf(false)

    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mesero - ${usuarioLogueado.nombre}") },
                actions = {

                    TextButton(

                        onClick = {

                            mostrarPedidos = true

                        }

                    ) {

                        Text("Pedidos")

                    }

                    TextButton(

                        onClick = onLogout

                    ) {

                        Text("Salir")

                    }

                }
            )
        }
    ) { innerPadding ->

        when {

            // Mostrar PreCuenta
            mostrarPreCuenta && pedidoActual?.pedidoActual != null -> {

                PreCuentaScreen(

                    pedido = pedidoActual!!.pedidoActual!!,

                    onCerrar = {

                        mostrarPreCuenta = false

                    }

                )

            }

            // Mostrar lista de pedidos
            mostrarPedidos -> {

                PedidosScreen(

                    pedidoRepository = pedidoRepository,

                    onVolver = {

                        mostrarPedidos = false

                    }

                )

            }

            // Mostrar toma de comanda
            mostrarComanda && pedidoActual?.pedidoActual != null -> {

                TomaComandaScreen(

                    pedido = pedidoActual!!.pedidoActual!!,

                    productoRepository = productoRepository,

                    pedidoRepository = pedidoRepository,

                    onGuardarComanda = {

                        mostrarComanda = false

                    },

                    onMostrarPreCuenta = {

                        mostrarPreCuenta = true

                    },

                    onVolver = {

                        mostrarComanda = false

                    }

                )

            }

            // Mostrar mesas
            else -> {

                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)

                ) {

                    LazyVerticalGrid(

                        columns = GridCells.Fixed(3),

                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),

                        horizontalArrangement = Arrangement.spacedBy(12.dp),

                        verticalArrangement = Arrangement.spacedBy(12.dp)

                    ) {

                        items(mesas) { mesa ->

                            MesaCard(

                                mesa = mesa,

                                onClick = {

                                    if (mesa.pedidoActual == null) {

                                        mesa.pedidoActual =
                                            pedidoService.crearPedido(
                                                mesa,
                                                usuarioLogueado
                                            )

                                    }

                                    pedidoActual = mesa
                                    mostrarComanda = true

                                }

                            )

                        }

                    }

                }

            }

        }

    }
}