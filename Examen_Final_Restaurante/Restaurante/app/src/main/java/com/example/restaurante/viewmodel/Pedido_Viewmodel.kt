package com.example.restaurante.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurante.model.entities.ItemPedidoEntity
import com.example.restaurante.model.entities.PedidoEntity
import com.example.restaurante.repository.PedidoRepository
import kotlinx.coroutines.launch
import com.example.restaurante.mapper.toEntity
import com.example.restaurante.model.Pedido.Pedido
class PedidoViewModel(

    private val repository: PedidoRepository

) : ViewModel() {

    fun guardarPedido(

        pedido: PedidoEntity,

        items: List<ItemPedidoEntity>,

        onFinish: () -> Unit

    ) {

        viewModelScope.launch {

            repository.guardarPedido(
                pedido,
                items
            )

            onFinish()

        }

    }

    fun obtenerPedidos(

        onResult: (List<PedidoEntity>) -> Unit

    ) {

        viewModelScope.launch {

            onResult(
                repository.obtenerPedidos()
            )

        }

    }

    fun guardarComanda(

        pedido: Pedido,

        onFinish: () -> Unit

    ) {

        viewModelScope.launch {

            val pedidoEntity = pedido.toEntity()

            val itemsEntity = pedido.obtenerItems().map {

                ItemPedidoEntity(

                    pedidoId = 0, // el Repository lo reemplazará

                    productoId = it.producto.id,

                    cantidad = it.cantidad,

                    precioUnitario = it.producto.precio

                )

            }

            repository.guardarPedido(
                pedidoEntity,
                itemsEntity
            )

            onFinish()

        }

    }

}