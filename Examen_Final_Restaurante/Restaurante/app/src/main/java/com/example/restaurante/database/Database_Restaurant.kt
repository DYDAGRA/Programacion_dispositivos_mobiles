package com.example.restaurante.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurante.database.converters.CategoriaConverter
import com.example.restaurante.database.converters.EstadoMesaConverter
import com.example.restaurante.database.converters.EstadoPedidoConverter
import com.example.restaurante.database.converters.MetodoPagoConverter
import com.example.restaurante.database.converters.RolConverter
import com.example.restaurante.database.dao.ProductoDao
import com.example.restaurante.model.Productos.Producto
import com.example.restaurante.model.Usuarios.Usuario
import com.example.restaurante.database.dao.UsuarioDao
import com.example.restaurante.model.entities.MesaEntity
import com.example.restaurante.model.entities.PedidoEntity
import com.example.restaurante.model.entities.ItemPedidoEntity
import com.example.restaurante.database.dao.MesaDao
import com.example.restaurante.database.dao.PedidoDao
import com.example.restaurante.database.dao.ItemPedidoDao
@Database(
    entities = [
        Producto::class,
        Usuario::class,
        MesaEntity::class,
        PedidoEntity::class,
        ItemPedidoEntity::class
    ],
    version = 3,
    exportSchema = false
)

@TypeConverters(
    CategoriaConverter::class,
    EstadoMesaConverter::class,
    EstadoPedidoConverter::class,
    MetodoPagoConverter::class,
    RolConverter::class
)

abstract class RestauranteDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao
    abstract fun usuarioDao(): UsuarioDao

    abstract fun mesaDao(): MesaDao

    abstract fun pedidoDao(): PedidoDao

    abstract fun itemPedidoDao(): ItemPedidoDao

}