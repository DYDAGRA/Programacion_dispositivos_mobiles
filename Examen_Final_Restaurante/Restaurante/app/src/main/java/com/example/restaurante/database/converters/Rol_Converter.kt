package com.example.restaurante.database.converters
import androidx.room.TypeConverter
import com.example.restaurante.model.Estados.Rol

class RolConverter {

    @TypeConverter
    fun fromRol(rol: Rol): String {
        return rol.name
    }

    @TypeConverter
    fun toRol(valor: String): Rol {
        return Rol.valueOf(valor)
    }
}