package com.example.restaurante.database.dao

import androidx.room.*
import com.example.restaurante.model.entities.ItemPedidoEntity

@Dao
interface ItemPedidoDao {

    @Insert
    suspend fun insertar(item: ItemPedidoEntity)

    @Insert
    suspend fun insertarTodos(items: List<ItemPedidoEntity>)

    @Update
    suspend fun actualizar(item: ItemPedidoEntity)

    @Delete
    suspend fun eliminar(item: ItemPedidoEntity)

    @Query("SELECT * FROM detalle_pedido WHERE pedidoId = :pedidoId")
    suspend fun obtenerPorPedido(pedidoId: Int): List<ItemPedidoEntity>

}