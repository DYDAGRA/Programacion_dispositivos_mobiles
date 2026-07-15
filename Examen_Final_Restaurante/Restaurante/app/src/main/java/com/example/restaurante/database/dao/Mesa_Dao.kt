package com.example.restaurante.database.dao

import androidx.room.*
import com.example.restaurante.model.entities.MesaEntity

@Dao
interface MesaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(mesa: MesaEntity)

    @Update
    suspend fun actualizar(mesa: MesaEntity)

    @Delete
    suspend fun eliminar(mesa: MesaEntity)

    @Query("SELECT * FROM mesas ORDER BY numero")
    suspend fun obtenerTodas(): List<MesaEntity>

    @Query("SELECT * FROM mesas WHERE numero = :numero")
    suspend fun buscarPorNumero(numero: Int): MesaEntity?

    @Query("SELECT COUNT(*) FROM mesas")
    suspend fun contarMesas(): Int

}