package com.example.restaurante.model.Pedido

import com.example.restaurante.model.Estados.EstadoPedido
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.model.Usuarios.Mozo
import java.time.LocalDateTime

class Pedido(

    val id: Int,

    val mesa: Mesa,

    val mozo: Mozo

) {

    val fecha = LocalDateTime.now()

    var estado = EstadoPedido.ACTIVO

    private val items = mutableListOf<ItemPedido>()


    fun agregarProducto(
        producto: Producto,
        cantidad: Int
    ) {

        val itemExistente = items.find {
            it.producto.id == producto.id
        }

        if (itemExistente != null) {

            itemExistente.cantidad += cantidad

        } else {

            items.add(
                ItemPedido(producto, cantidad)
            )

        }
    }

    fun eliminarProducto(producto: Producto) {

        items.removeIf {
            it.producto.id == producto.id
        }

    }

    fun modificarCantidad(
        producto: Producto,
        nuevaCantidad: Int
    ) {

        val item = items.find {
            it.producto.id == producto.id
        }

        item?.let {

            if (nuevaCantidad <= 0) {

                eliminarProducto(producto)

            } else {

                it.cantidad = nuevaCantidad

            }

        }

    }

    fun obtenerItems(): List<ItemPedido> {

        return items.toList()

    }


    fun calcularTotal(): Double {

        return items.sumOf {

            it.calcularSubtotal()

        }

    }


    fun cantidadProductos(): Int {

        return items.sumOf {

            it.cantidad

        }

    }

    fun vaciarPedido() {

        items.clear()

    }

    fun estaVacio(): Boolean {

        return items.isEmpty()

    }

}
