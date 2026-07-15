package com.example.restaurante.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurante.model.Estados.EstadoMesa

@Entity(tableName = "mesas")
data class MesaEntity(

    @PrimaryKey
    val numero: Int,

    var estado: EstadoMesa = EstadoMesa.LIBRE

)