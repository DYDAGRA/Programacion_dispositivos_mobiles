package com.example.restaurante.repository

import com.example.restaurante.database.dao.MesaDao
import com.example.restaurante.model.entities.MesaEntity

class MesaRepository(

    private val mesaDao: MesaDao

) {

    suspend fun insertar(mesa: MesaEntity) {
        mesaDao.insertar(mesa)
    }

    suspend fun actualizar(mesa: MesaEntity) {
        mesaDao.actualizar(mesa)
    }

    suspend fun obtenerTodas(): List<MesaEntity> {
        return mesaDao.obtenerTodas()
    }

    suspend fun buscarPorNumero(numero: Int): MesaEntity? {
        return mesaDao.buscarPorNumero(numero)
    }

    suspend fun inicializarMesas() {

        if (mesaDao.contarMesas() == 0) {

            for (i in 1..12) {

                mesaDao.insertar(
                    MesaEntity(
                        numero = i
                    )
                )

            }

        }

    }
}