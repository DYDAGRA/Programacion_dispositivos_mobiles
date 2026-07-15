package com.example.restaurante.model.Usuarios

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurante.model.Estados.Rol

@Entity(tableName = "usuarios")
data class Usuario(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var nombre: String,

    var usuario: String,

    var contraseña: String,

    var rol: Rol,

    var activo: Boolean = true

)