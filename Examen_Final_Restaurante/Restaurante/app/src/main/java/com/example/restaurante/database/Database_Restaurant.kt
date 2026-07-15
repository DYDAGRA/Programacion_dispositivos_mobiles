package com.example.restaurante.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurante.database.converters.CategoriaConverter
import com.example.restaurante.database.converters.EstadoMesaConverter
import com.example.restaurante.database.converters.EstadoPedidoConverter
import com.example.restaurante.database.converters.MetodoPagoConverter
import com.example.restaurante.database.converters.RolConverter
import com.example.restaurante.database.dao.ProductoDao
import com.example.restaurante.model.Productos.Producto

@Database(
    entities = [
        Producto::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    CategoriaConverter::class,
    EstadoMesaConverter::class,
    EstadoPedidoConverter::class,
    MetodoPagoConverter::class,
    RolConverter::class
)

abstract class RestauranteDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

}