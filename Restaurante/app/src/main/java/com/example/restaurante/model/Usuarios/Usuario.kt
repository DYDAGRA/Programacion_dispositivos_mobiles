package com.example.restaurante.model.Usuarios

import com.example.restaurante.model.Estados.Rol

open class Usuario(

    val id: Int,

    var nombre: String,

    var usuario: String,

    var contrasena: String,

    val rol: Rol

)