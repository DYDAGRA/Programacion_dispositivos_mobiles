package com.example.restaurante.model.Pedido

import com.example.restaurante.model.Estados.EstadoPedido
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.model.Usuarios.Mozo
import java.time.LocalDateTime
data class ItemPedido(

    val producto: Producto,

    var cantidad: Int

){

    fun calcularSubtotal(): Double{

        return producto.precio * cantidad

    }

}