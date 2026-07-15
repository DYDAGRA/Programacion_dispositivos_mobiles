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

    @Query("SELECT * FROM productos WHERE disponible = 1")
    suspend fun obtenerDisponibles(): List<Producto>

    @Query("""UPDATE productos SET stock = stock - :cantidad WHERE id = :productoId """)
    suspend fun descontarStock(productoId: Int, cantidad: Int)

}