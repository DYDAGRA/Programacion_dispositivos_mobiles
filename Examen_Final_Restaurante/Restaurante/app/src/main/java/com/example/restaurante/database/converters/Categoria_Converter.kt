package com.example.restaurante.database.converters

import androidx.room.TypeConverter
import com.example.restaurante.model.Estados.Categoria

class CategoriaConverter {

    @TypeConverter
    fun fromCategoria(categoria: Categoria): String {
        return categoria.name
    }

    @TypeConverter
    fun toCategoria(valor: String): Categoria {
        return Categoria.valueOf(valor)
    }
}