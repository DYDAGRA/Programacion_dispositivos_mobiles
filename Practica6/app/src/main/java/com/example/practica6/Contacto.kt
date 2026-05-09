package com.example.practica6

data class Contacto(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val favorito: Boolean = false
)