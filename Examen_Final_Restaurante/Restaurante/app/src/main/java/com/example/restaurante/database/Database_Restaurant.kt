package com.example.restaurante.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurante.database.dao.ProductoDao
import com.example.restaurante.model.Productos.Producto

@Database(
    entities = [
        Producto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RestauranteDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

}