package com.example.restaurante.repository
import com.example.restaurante.database.dao.ProductoDao
import com.example.restaurante.model.Productos.Producto

class ProductoRepository(

    private val productoDao: ProductoDao

) {

    suspend fun insertar(producto: Producto) {

        productoDao.insertar(producto)

    }

    suspend fun actualizar(producto: Producto) {

        productoDao.actualizar(producto)

    }

    suspend fun eliminar(producto: Producto) {

        productoDao.eliminar(producto)

    }

    suspend fun obtenerTodos(): List<Producto> {

        return productoDao.obtenerTodos()

    }

    suspend fun obtenerDisponibles(): List<Producto> {

        return productoDao.obtenerDisponibles()

    }

    suspend fun buscarPorId(id: Int): Producto? {

        return productoDao.buscarPorId(id)

    }

    suspend fun descontarStock(
        productoId: Int,
        cantidad: Int
    ) {
        productoDao.descontarStock(
            productoId,
            cantidad
        )
    }

}