package com.example.restaurante.model.Productos

import com.example.restaurante.model.Estados.Categoria

data class Producto(

    val id: Int,

    var nombre: String,

    var precio: Double,

    var categoria: Categoria,

    var disponible: Boolean = true

)