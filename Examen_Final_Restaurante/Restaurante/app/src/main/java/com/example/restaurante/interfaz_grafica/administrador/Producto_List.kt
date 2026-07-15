package com.example.restaurante.interfaz_grafica.administrador

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.restaurante.interfaz_grafica.componentes.ProductoCard
import com.example.restaurante.model.Productos.Producto
@Composable
fun ProductoList(

    productos: List<Producto>,

    buscar: String,

    onEditar: (Producto) -> Unit,

    onEliminar: (Producto) -> Unit

) {

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)

    ) {

        items(

            productos.filter {

                it.nombre.contains(
                    buscar,
                    ignoreCase = true
                )

            }

        ) { producto ->

            ProductoCard(

                producto = producto,

                onEditar = {

                    onEditar(producto)

                },

                onEliminar = {

                    onEliminar(producto)

                }

            )

        }

    }

}