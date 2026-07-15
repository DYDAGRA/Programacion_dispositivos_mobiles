package com.example.restaurante.database.converters
import androidx.room.TypeConverter
import com.example.restaurante.model.Estados.EstadoPedido

class EstadoPedidoConverter {

    @TypeConverter
    fun fromEstado(estado: EstadoPedido): String {
        return estado.name
    }

    @TypeConverter
    fun toEstado(valor: String): EstadoPedido {
        return EstadoPedido.valueOf(valor)
    }
}