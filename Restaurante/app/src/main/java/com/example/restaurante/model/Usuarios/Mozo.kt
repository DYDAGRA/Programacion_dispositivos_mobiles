package com.example.restaurante.model.Usuarios

import com.example.restaurante.model.Estados.Rol

class Mozo(

    id: Int,

    nombre: String,

    usuario: String,

    contrasena: String

) : Usuario(

    id,

    nombre,

    usuario,

    contrasena,

    Rol.MOZO

)