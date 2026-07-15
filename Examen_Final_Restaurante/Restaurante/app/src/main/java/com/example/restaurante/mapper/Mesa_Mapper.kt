package com.example.restaurante.mapper

import com.example.restaurante.model.Pedido.Mesa
import com.example.restaurante.model.entities.MesaEntity

fun MesaEntity.toMesa(): Mesa {

    return Mesa(
        numero = numero
    ).apply {
        estado = this@toMesa.estado
    }

}

fun Mesa.toEntity(): MesaEntity {

    return MesaEntity(
        numero = numero,
        estado = estado
    )

}