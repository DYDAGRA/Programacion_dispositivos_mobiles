package com.example.restaurante.interfaz_grafica

import androidx.compose.runtime.*
import com.example.restaurante.interfaz_grafica.Login.LoginScreen
import com.example.restaurante.interfaz_grafica.administrador.AdminScreen
import com.example.restaurante.interfaz_grafica.mozo.MeseroScreen
import com.example.restaurante.model.Estados.Rol
import com.example.restaurante.model.Usuarios.Usuario
import com.example.restaurante.repository.ProductoRepository
import com.example.restaurante.repository.UsuarioRepository
import com.example.restaurante.repository.PedidoRepository
import com.example.restaurante.interfaz_grafica.cocina.CocinaScreen
@Composable
fun AppNavigation(
    usuarioRepository: UsuarioRepository,
    productoRepository: ProductoRepository,
    pedidoRepository: PedidoRepository
) {

    var currentUser by remember {
        mutableStateOf<Usuario?>(null)
    }

    if (currentUser == null) {

        LoginScreen(

            repository = usuarioRepository,

            onLoginSuccess = {

                currentUser = it

            }

        )

    } else {

        when (currentUser!!.rol) {

            Rol.ADMINISTRADOR -> {
                AdminScreen(
                    productoRepository = productoRepository,
                    pedidoRepository = pedidoRepository,
                    onLogout = { currentUser = null },
                    onVolver = { currentUser = null }
                )
            }

            Rol.MOZO -> {
                MeseroScreen(
                    usuarioLogueado = currentUser!!,
                    productoRepository = productoRepository,
                    pedidoRepository = pedidoRepository,
                    onLogout = { currentUser = null }
                )
            }

            Rol.COCINA -> {
                CocinaScreen(
                    pedidoRepository = pedidoRepository,
                    onLogout = { currentUser = null },
                    onVolver = { currentUser = null }
                )
            }
        }

    }

}