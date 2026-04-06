    //clase
class Producto{
    //variables con set and get
    var precio: Float = 0.0f
        get(){return field}
        set(value){
            if (precio>=0){ field = value }
            else 0.0
        }
    var descuento: Float=0.0f
    get(){return field}
    set(value){
        when{
            value<0.0 -> 0.0
            value>100.0 -> 100.0
            else -> value
        }
    }
    //Constructor
    constructor(precio: Float, descuento: Float) {
        this.precio = precio
        this.descuento = descuento
    }

    //Funcion descuento
    fun descuento_aplicado():Float{
        val precio_final_descuento = precio* (descuento/100)
        return precio_final_descuento
    }
    //mostrar cambios
    fun mostrar_producto(){
        println("Precio original del producto : $precio")
        println("Descuento aplicado : $descuento")
        println("Precio del producto con descuento : ${descuento_aplicado()}")
    }
}

    fun main() {
        // Objeto de la clase
        val producto1 = Producto(150.0f, 20.0f)

        producto1.mostrar_producto()
        println (" ")
        println ("Cambiando los valores del producto: ")
        println (" ")
        // Cambiando los valores del objeto
        producto1.precio = 299.99f
        producto1.descuento = 15.5f
        producto1.mostrar_producto()
    }