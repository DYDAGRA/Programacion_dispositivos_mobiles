package com.example.restaurante.model.Pedido

import com.example.restaurante.model.Estados.EstadoMesa

class Mesa(

    val numero: Int

) {

    var estado = EstadoMesa.LIBRE

    var pedidoActual: Pedido? = null

    fun estaLibre(): Boolean {


        return pedidoActual == null

    }

}