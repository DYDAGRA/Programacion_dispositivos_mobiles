package com.example.restaurante.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurante.model.Estados.EstadoPedido
import com.example.restaurante.model.Estados.MetodoPago

@Entity(tableName = "pedidos")
data class PedidoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val numeroMesa: Int,

    val usuarioId: Int,

    var observacion: String = "",

    var estado: EstadoPedido = EstadoPedido.ACTIVO,

    val fechaCreacion: Long = System.currentTimeMillis(),

    var fechaFin: Long? = null,

    var metodoPago: MetodoPago? = null

)