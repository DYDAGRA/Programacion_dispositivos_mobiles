package com.example.restaurante.mapper

import com.example.restaurante.model.Pedido.Pedido
import com.example.restaurante.model.entities.PedidoEntity

fun Pedido.toEntity(): PedidoEntity {

    return PedidoEntity(

        id = id,

        numeroMesa = mesa.numero,

        usuarioId = usuario.id,

        observacion = observacion,

        estado = estado,

        fechaCreacion = fechaCreacion
            .atZone(java.time.ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli(),

        fechaFin = fechaFin?.atZone(java.time.ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli(),

        metodoPago = metodopago

    )

}