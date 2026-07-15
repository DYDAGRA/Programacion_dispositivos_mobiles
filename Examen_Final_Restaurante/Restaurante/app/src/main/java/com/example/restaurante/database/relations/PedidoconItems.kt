package com.example.restaurante.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.restaurante.model.entities.ItemPedidoEntity
import com.example.restaurante.model.entities.PedidoEntity

data class PedidoConItems(

    @Embedded
    val pedido: PedidoEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "pedidoId"
    )
    val items: List<ItemPedidoEntity>

)