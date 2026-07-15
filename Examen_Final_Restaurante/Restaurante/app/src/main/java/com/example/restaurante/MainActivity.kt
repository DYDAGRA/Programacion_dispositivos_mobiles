package com.example.restaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.restaurante.database.DatabaseProvider
import com.example.restaurante.interfaz_grafica.AppNavigation
import com.example.restaurante.model.Estados.Categoria
import com.example.restaurante.model.Estados.Rol
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.model.Usuarios.Usuario
import com.example.restaurante.repository.ProductoRepository
import com.example.restaurante.repository.UsuarioRepository
import com.example.restaurante.ui.theme.RestauranteTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.restaurante.repository.PedidoRepository
class MainActivity : ComponentActivity() {

    private val database by lazy { DatabaseProvider.getDatabase(this) }

    private val usuarioRepository by lazy {
        UsuarioRepository(database.usuarioDao())
    }

    private val productoRepository by lazy {
        ProductoRepository(database.productoDao())
    }

    private val pedidoRepository by lazy {
        PedidoRepository(
            database.pedidoDao(),
            database.itemPedidoDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        
        insertarDatosPrueba()

        setContent {
            RestauranteTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(
                        usuarioRepository = usuarioRepository,
                        productoRepository = productoRepository,
                        pedidoRepository = pedidoRepository
                    )
                }
            }
        }
    }

    private fun insertarDatosPrueba() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Usuarios
                if (usuarioRepository.obtenerTodos().isEmpty()) {
                    usuarioRepository.insertar(
                        Usuario(
                            nombre = "Administrador",
                            usuario = "admin",
                            contraseña = "1234",
                            rol = Rol.ADMINISTRADOR
                        )
                    )
                    usuarioRepository.insertar(
                        Usuario(
                            nombre = "Juan Pérez",
                            usuario = "mozo",
                            contraseña = "1234",
                            rol = Rol.MOZO
                        )
                    )
                    usuarioRepository.insertar(
                        Usuario(
                            nombre = "Chef",
                            usuario = "cocina",
                            contraseña = "1234",
                            rol = Rol.COCINA
                        )
                    )
                }

                // Productos
                if (productoRepository.obtenerTodos().isEmpty()) {
                    productoRepository.insertar(
                        Producto(nombre = "Ceviche", precio = 25.0, categoria = Categoria.ENTRADAS, stock = 30)
                    )
                    productoRepository.insertar(
                        Producto(nombre = "Lomo Saltado", precio = 35.0, categoria = Categoria.PLATOS, stock = 25)
                    )
                    productoRepository.insertar(
                        Producto(nombre = "Inka Cola", precio = 8.0, categoria = Categoria.BEBIDAS, stock = 60)
                    )
                    productoRepository.insertar(
                        Producto(nombre = "Cheesecake", precio = 18.0, categoria = Categoria.POSTRES, stock = 15)
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}