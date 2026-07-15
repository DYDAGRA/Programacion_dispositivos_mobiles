package com.example.restaurante.interfaz_grafica.administrador

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.interfaz_grafica.componentes.ProductoCard
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.repository.ProductoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.restaurante.repository.PedidoRepository
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    productoRepository: ProductoRepository,
    pedidoRepository: PedidoRepository,
    onLogout: () -> Unit,
    onVolver: () -> Unit
) {
    val productos = remember { mutableStateListOf<Producto>() }
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var productoEditar by remember { mutableStateOf<Producto?>(null) }
    var buscar by remember { mutableStateOf("") }
    var mostrarVentas by remember { mutableStateOf(false) }

    // Cargar productos
    LaunchedEffect(Unit) {
        productos.clear()
        productos.addAll(productoRepository.obtenerTodos())
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Administrador - Productos") },
                actions = {

                    TextButton(
                        onClick = {
                            mostrarVentas = true
                        }
                    ) {
                        Text("Ventas")
                    }

                    TextButton(
                        onClick = onLogout
                    ) {
                        Text("Salir")
                    }

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        if (mostrarVentas) {

            VentasScreen(

                pedidoRepository = pedidoRepository,

                onVolver = {

                    mostrarVentas = false

                }

            )

        } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = buscar,
                onValueChange = { buscar = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Buscar producto") }
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(productos.filter { it.nombre.contains(buscar, ignoreCase = true) }) { producto ->
                    ProductoCard(
                        producto = producto,
                        onEditar = {
                            productoEditar = producto
                            showEditDialog = true
                        },
                        onEliminar = {
                            CoroutineScope(Dispatchers.IO).launch {
                                productoRepository.eliminar(producto)
                            }
                            productos.remove(producto)
                        }
                    )
                }
            }
        }

        if (showAddDialog) {
            AddProductoDialog(
                onDismiss = { showAddDialog = false },
                onSave = { nuevo ->
                    CoroutineScope(Dispatchers.IO).launch {
                        productoRepository.insertar(nuevo)
                    }
                    productos.add(nuevo)
                    showAddDialog = false
                }
            )
        }

        if (showEditDialog && productoEditar != null) {
            EditProductoDialog(
                producto = productoEditar!!,
                onDismiss = {
                    showEditDialog = false
                    productoEditar = null
                },
                onSave = { actualizado ->
                    CoroutineScope(Dispatchers.IO).launch {
                        productoRepository.actualizar(actualizado)
                    }
                    val index = productos.indexOfFirst { it.id == actualizado.id }
                    if (index != -1) productos[index] = actualizado
                    showEditDialog = false
                    productoEditar = null
                }
            )
        }
    }

    }

}

