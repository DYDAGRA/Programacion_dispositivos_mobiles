package com.example.restaurante.repository

import com.example.restaurante.database.dao.ItemPedidoDao
import com.example.restaurante.database.dao.PedidoDao
import com.example.restaurante.model.Pedido.Pedido
import com.example.restaurante.model.entities.ItemPedidoEntity
import com.example.restaurante.model.entities.PedidoEntity

class PedidoRepository(

    private val pedidoDao: PedidoDao,
    private val itemPedidoDao: ItemPedidoDao

) {

    suspend fun guardarPedido(
        pedido: PedidoEntity,
        items: List<ItemPedidoEntity>
    ) {

        val id = pedidoDao.insertar(pedido)

        items.forEach {

            itemPedidoDao.insertar(
                it.copy(
                    pedidoId = id.toInt()
                )
            )

        }

    }

    suspend fun obtenerPedidos(): List<PedidoEntity> {

        return pedidoDao.obtenerTodos()

    }

    suspend fun obtenerDetalle(
        pedidoId: Int
    ): List<ItemPedidoEntity> {

        return itemPedidoDao.obtenerPorPedido(pedidoId)

    }

    suspend fun obtenerPedidoCompleto(
        pedidoId: Int
    ) = pedidoDao.obtenerPedidoCompleto(pedidoId)

    suspend fun guardarPedido(
        pedido: Pedido
    ) {

        val pedidoEntity = PedidoEntity(

            numeroMesa = pedido.mesa.numero,

            usuarioId = pedido.usuario.id,

            observacion = pedido.observacion,

            estado = pedido.estado,

            fechaCreacion = pedido.fechaCreacion
                .atZone(java.time.ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),

            fechaFin = pedido.fechaFin
                ?.atZone(java.time.ZoneId.systemDefault())
                ?.toInstant()
                ?.toEpochMilli(),

            metodoPago = pedido.metodopago

        )

        val pedidoId = pedidoDao.insertar(pedidoEntity).toInt()

        pedido.obtenerItems().forEach { item ->

            itemPedidoDao.insertar(

                ItemPedidoEntity(

                    pedidoId = pedidoId,

                    productoId = item.producto.id,

                    cantidad = item.cantidad,

                    precioUnitario = item.producto.precio

                )

            )

        }

    }
    suspend fun actualizarPedido(
        pedido: PedidoEntity
    ) {

        pedidoDao.actualizar(pedido)

    }

}