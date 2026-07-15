package com.example.restaurante.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.repository.ProductoRepository
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val repository: ProductoRepository
) : ViewModel() {

    fun insertar(producto: Producto) {
        viewModelScope.launch {
            repository.insertar(producto)
        }
    }

    fun actualizar(producto: Producto) {
        viewModelScope.launch {
            repository.actualizar(producto)
        }
    }

    fun eliminar(producto: Producto) {
        viewModelScope.launch {
            repository.eliminar(producto)
        }
    }
}