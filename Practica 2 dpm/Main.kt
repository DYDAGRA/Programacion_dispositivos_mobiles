//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
