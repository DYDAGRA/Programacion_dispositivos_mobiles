package com.example.restaurante.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.repository.PedidoRepository

class PedidoViewModelFactory(

    private val repository: PedidoRepository

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {

        if (modelClass.isAssignableFrom(PedidoViewModel::class.java)) {

            return PedidoViewModel(repository) as T

        }

        throw IllegalArgumentException("ViewModel desconocido")

    }

}