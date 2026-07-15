package com.example.restaurante.service

import com.example.restaurante.model.Estados.EstadoMesa
import com.example.restaurante.model.Pedido.Mesa
import com.example.restaurante.model.Pedido.Pedido
import com.example.restaurante.model.Productos.Producto

class PedidoService {

    private var contadorPedidos = 1

    fun crearPedido(
        mesa: Mesa,
        mozo: Mozo
    ): Pedido {

        if (mesa.pedidoActual != null) {
            throw IllegalStateException("La mesa ya tiene un pedido activo.")
        }

        val pedido = Pedido(
            contadorPedidos++,
            mesa,
            mozo
        )

        mesa.pedidoActual = pedido
        mesa.estado = EstadoMesa.OCUPADA

        return pedido
    }

    fun agregarProducto(
        pedido: Pedido,
        producto: Producto,
        cantidad: Int
    ) {

        pedido.agregarProducto(producto, cantidad)

    }

    fun eliminarProducto(
        pedido: Pedido,
        producto: Producto
    ) {

        pedido.eliminarProducto(producto)

    }


    fun modificarCantidad(
        pedido: Pedido,
        producto: Producto,
        cantidad: Int
    ) {

        pedido.modificarCantidad(producto, cantidad)

    }

    fun calcularTotal(
        pedido: Pedido
    ): Double {

        return pedido.calcularTotal()

    }

    fun cerrarPedido(
        pedido: Pedido
    ) {

        pedido.estado = com.example.restaurante.model.Estados.EstadoPedido.PAGADO

        pedido.mesa.estado = EstadoMesa.LIBRE

        pedido.mesa.pedidoActual = null

    }

    fun anularPedido(
        pedido: Pedido
    ) {

        pedido.estado = com.example.restaurante.model.Estados.EstadoPedido.ANULADO

        pedido.mesa.estado = EstadoMesa.LIBRE

        pedido.mesa.pedidoActual = null

    }

}