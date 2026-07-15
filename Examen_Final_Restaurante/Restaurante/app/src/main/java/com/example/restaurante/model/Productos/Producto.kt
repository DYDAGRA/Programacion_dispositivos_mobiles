package com.example.restaurante.model.Productos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurante.model.Estados.Categoria

@Entity(tableName = "productos")
data class Producto(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var nombre: String,

    var precio: Double,

    var categoria: Categoria,

    var stock: Int = 0,

    var disponible: Boolean = true

)