package com.example.practica4
import java.io.Serializable

data class Usuario(
    val nombre: String,
    val edad: Int,
    val ciudad: String,
    val correo: String
) : Serializable