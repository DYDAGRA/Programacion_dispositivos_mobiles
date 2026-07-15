package com.example.restaurante.database.dao

import androidx.room.*
import com.example.restaurante.model.Estados.EstadoPedido
import com.example.restaurante.model.entities.PedidoEntity
import androidx.room.Transaction
import com.example.restaurante.database.relations.PedidoConItems
import com.example.restaurante.model.entities.ItemPedidoEntity
@Dao
interface PedidoDao {

    @Insert
    suspend fun insertar(pedido: PedidoEntity): Long

    @Update
    suspend fun actualizar(pedido: PedidoEntity)

    @Delete
    suspend fun eliminar(pedido: PedidoEntity)

    @Query("SELECT * FROM pedidos ORDER BY fechaCreacion DESC")
    suspend fun obtenerTodos(): List<PedidoEntity>

    @Query("SELECT * FROM pedidos WHERE id = :id")
    suspend fun buscarPorId(id: Int): PedidoEntity?

    @Query("SELECT * FROM pedidos WHERE numeroMesa = :mesa")
    suspend fun buscarPorMesa(mesa: Int): List<PedidoEntity>

    @Query("SELECT * FROM pedidos WHERE estado = :estado")
    suspend fun buscarPorEstado(estado: EstadoPedido): List<PedidoEntity>

    @Transaction
    @Query("SELECT * FROM pedidos WHERE id = :pedidoId")
    suspend fun obtenerPedidoCompleto(
        pedidoId: Int
    ): PedidoConItems
}