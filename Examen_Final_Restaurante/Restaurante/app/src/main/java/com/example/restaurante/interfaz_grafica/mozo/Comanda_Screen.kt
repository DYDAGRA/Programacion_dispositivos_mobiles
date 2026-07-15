package com.example.restaurante.interfaz_grafica.mozo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Estados.EstadoMesa
import com.example.restaurante.model.Estados.EstadoPedido
import com.example.restaurante.model.Pedido.Pedido
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.repository.ProductoRepository
import com.example.restaurante.repository.PedidoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TomaComandaScreen(
    pedido: Pedido,

    productoRepository: ProductoRepository,

    pedidoRepository: PedidoRepository,

    onGuardarComanda: () -> Unit,

    onMostrarPreCuenta: () -> Unit,

    onVolver: () -> Unit

) {

    val productosDisponibles = remember { mutableStateListOf<Producto>() }

    val scope = rememberCoroutineScope()

    fun recargarProductos() {

        scope.launch {

            productosDisponibles.clear()

            productosDisponibles.addAll(
                productoRepository.obtenerDisponibles()
            )

        }

    }

    var subtotal by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(Unit) {

        recargarProductos()

        subtotal = pedido.calcularTotal()

    }

    val itemsPedido = pedido.obtenerItems()

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {
                    Text("Mesa ${pedido.mesa.numero}")
                },

                navigationIcon = {

                    IconButton(
                        onClick = onVolver
                    ) {
                        Text("←")
                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)

        ) {

            LazyColumn(

                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)

            ) {

                items(productosDisponibles) { producto ->

                    ProductoItem(

                        producto = producto,

                        onAgregar = {

                            pedido.agregarProducto(
                                producto,
                                1
                            )

                            subtotal = pedido.calcularTotal()

                        }

                    )

                }

            }

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {

                Column(

                    modifier = Modifier.padding(16.dp)

                ) {

                    Text(
                        "Comanda Actual",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    itemsPedido.forEach { item ->

                        Row(

                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween

                        ) {

                            Text(
                                "${item.cantidad} x ${item.producto.nombre}"
                            )

                            Text(
                                "S/. ${"%.2f".format(item.calcularSubtotal())}"
                            )

                        }

                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    Text(

                        text = "Total: S/. ${"%.2f".format(subtotal)}",

                        style = MaterialTheme.typography.headlineSmall

                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(

                        horizontalArrangement =
                            Arrangement.spacedBy(8.dp)

                    ) {

                        Button(

                            onClick = {

                                CoroutineScope(Dispatchers.IO).launch {

                                    pedidoRepository.guardarPedido(pedido)

                                }

                                onGuardarComanda()

                            },

                            modifier = Modifier.weight(1f)

                        ) {

                            Text("Guardar")

                        }

                        Button(

                            onClick = {

                                CoroutineScope(Dispatchers.IO).launch {

                                    pedido.obtenerItems().forEach {

                                        productoRepository.descontarStock(
                                            it.producto.id,
                                            it.cantidad
                                        )

                                    }

                                }

                                recargarProductos()

                                pedido.enviadoACocina = true
                                pedido.estado = EstadoPedido.ENVIADO

                                onGuardarComanda()

                            },

                            modifier = Modifier.weight(1f),

                            colors = ButtonDefaults.buttonColors(

                                containerColor =
                                    Color(0xFF2196F3)

                            )

                        ) {

                            Text("Enviar")

                        }

                        Button(

                            onClick = {

                                pedido.estado = EstadoPedido.PAGADO

                                CoroutineScope(Dispatchers.IO).launch {

                                    pedidoRepository.guardarPedido(pedido)

                                }

                                pedido.mesa.estado = EstadoMesa.LIBRE
                                pedido.mesa.pedidoActual = null

                                onGuardarComanda()

                            },

                            modifier = Modifier.weight(1f),

                            colors = ButtonDefaults.buttonColors(

                                containerColor =
                                    Color(0xFF4CAF50)

                            )

                        ) {

                            Text("Pagar")

                        }

                        Button(

                            onClick = {

                                onMostrarPreCuenta()

                            },

                            modifier = Modifier.weight(1f)

                        ) {

                            Text("PreCuenta")

                        }

                    }

                }

            }

        }

    }

}

@Composable
fun ProductoItem(
    producto: Producto,
    onAgregar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    producto.nombre,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    "S/. ${"%.2f".format(producto.precio)}"
                )

                Text(
                    "Stock: ${producto.stock}"
                )

                if (producto.stock == 0) {

                    Text(
                        text = "Sin stock",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }

            Button(
                onClick = onAgregar,
                enabled = producto.stock > 0
            ) {
                Text("Agregar")
            }

        }

    }
}