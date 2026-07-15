package com.example.restaurante.repository
import com.example.restaurante.database.dao.UsuarioDao
import com.example.restaurante.model.Estados.Rol
import com.example.restaurante.model.Usuarios.Usuario

class UsuarioRepository(

    private val usuarioDao: UsuarioDao

) {

    suspend fun insertar(usuario: Usuario) =
        usuarioDao.insertar(usuario)

    suspend fun actualizar(usuario: Usuario) =
        usuarioDao.actualizar(usuario)

    suspend fun eliminar(usuario: Usuario) =
        usuarioDao.eliminar(usuario)

    suspend fun obtenerTodos() =
        usuarioDao.obtenerTodos()

    suspend fun buscarPorId(id: Int) =
        usuarioDao.buscarPorId(id)

    suspend fun buscarPorUsuario(usuario: String) =
        usuarioDao.buscarPorUsuario(usuario)

    suspend fun iniciarSesion(
        usuario: String,
        contraseña: String
    ) =
        usuarioDao.iniciarSesion(usuario, contraseña)

    suspend fun obtenerPorRol(rol: Rol) =
        usuarioDao.obtenerPorRol(rol)

    suspend fun obtenerActivos() =
        usuarioDao.obtenerActivos()

}