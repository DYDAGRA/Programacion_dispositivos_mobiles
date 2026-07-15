package com.example.restaurante.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurante.model.Usuarios.Usuario
import com.example.restaurante.repository.UsuarioRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UsuarioRepository
) : ViewModel() {

    var usuarioLogueado: Usuario? = null
        private set

    fun iniciarSesion(
        usuario: String,
        contraseña: String,
        onResultado: (Boolean, Usuario?) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = repository.iniciarSesion(usuario, contraseña)
            usuarioLogueado = resultado
            onResultado(resultado != null, resultado)
        }
    }
}