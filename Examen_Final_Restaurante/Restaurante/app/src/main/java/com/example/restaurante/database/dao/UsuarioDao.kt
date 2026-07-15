package com.example.restaurante.database.dao

import androidx.room.*
import com.example.restaurante.model.Estados.Rol
import com.example.restaurante.model.Usuarios.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuario)

    @Update
    suspend fun actualizar(usuario: Usuario)

    @Delete
    suspend fun eliminar(usuario: Usuario)

    @Query("SELECT * FROM usuarios ORDER BY nombre")
    suspend fun obtenerTodos(): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun buscarPorId(id: Int): Usuario?

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario")
    suspend fun buscarPorUsuario(usuario: String): Usuario?

    @Query("""
        SELECT * FROM usuarios
        WHERE usuario = :usuario
        AND contraseña = :contraseña
        AND activo = 1
        LIMIT 1
    """)
    suspend fun iniciarSesion(
        usuario: String,
        contraseña: String
    ): Usuario?

    @Query("SELECT * FROM usuarios WHERE rol = :rol")
    suspend fun obtenerPorRol(
        rol: Rol
    ): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE activo = 1")
    suspend fun obtenerActivos(): List<Usuario>

}