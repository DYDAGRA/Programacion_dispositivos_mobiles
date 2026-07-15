package com.example.restaurante.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detalle_pedido")
data class ItemPedidoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val pedidoId: Int,

    val productoId: Int,

    val cantidad: Int,

    val precioUnitario: Double

)