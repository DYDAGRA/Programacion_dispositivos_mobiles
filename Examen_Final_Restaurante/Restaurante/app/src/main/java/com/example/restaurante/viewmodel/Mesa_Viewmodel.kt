package com.example.restaurante.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurante.model.entities.MesaEntity
import com.example.restaurante.repository.MesaRepository
import kotlinx.coroutines.launch

class MesaViewModel(
    private val repository: MesaRepository
) : ViewModel() {

    fun inicializarMesas() {
        viewModelScope.launch {
            repository.inicializarMesas()
        }
    }

    fun obtenerMesas(
        onResult: (List<MesaEntity>) -> Unit
    ) {
        viewModelScope.launch {
            onResult(repository.obtenerTodas())
        }
    }

}