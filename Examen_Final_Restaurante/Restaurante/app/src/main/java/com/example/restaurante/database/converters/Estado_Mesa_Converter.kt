package com.example.restaurante.database.converters
import androidx.room.TypeConverter
import com.example.restaurante.model.Estados.EstadoMesa

class EstadoMesaConverter {

    @TypeConverter
    fun fromEstado(estado: EstadoMesa): String {
        return estado.name
    }

    @TypeConverter
    fun toEstado(valor: String): EstadoMesa {
        return EstadoMesa.valueOf(valor)
    }
}