package com.example.restaurante.database.converters
import androidx.room.TypeConverter
import com.example.restaurante.model.Estados.MetodoPago

class MetodoPagoConverter {

    @TypeConverter
    fun fromMetodo(metodo: MetodoPago?): String? {
        return metodo?.name
    }

    @TypeConverter
    fun toMetodo(valor: String?): MetodoPago? {
        return valor?.let { MetodoPago.valueOf(it) }
    }
}