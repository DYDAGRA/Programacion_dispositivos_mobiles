package com.example.restaurante.database.dao

import androidx.room.*
import com.example.restaurante.model.Productos.Producto

@Dao
interface ProductoDao {

    @Insert
    suspend fun insertar(producto: Producto)

    @Update
    suspend fun actualizar(producto: Producto)

    @Delete
    suspend fun eliminar(producto: Producto)

    @Query("SELECT * FROM productos")
    suspend fun obtenerTodos(): List<Producto>

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun buscarPorId(id: Int): Producto?

}