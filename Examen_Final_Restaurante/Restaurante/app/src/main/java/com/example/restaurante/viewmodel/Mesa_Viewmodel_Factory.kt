package com.example.restaurante.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurante.repository.MesaRepository

class MesaViewModelFactory(
    private val repository: MesaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MesaViewModel::class.java)) {
            return MesaViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel no encontrado")
    }
}