//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    fun main() {
        // Crear producto usando constructor
        val producto1 = Producto(150.0f, 20.0f)

        producto1.mostrar_producto()

        // Probar validaciones del setter
        producto1.precio = -50.0f     // No permite precio negativo
        producto1.descuento = 150.0f   // No permite descuento > 100%

        println("\nDespués de intentar valores inválidos:")
        producto1.mostrar_producto()

        // Cambiar valores normalmente
        producto1.precio = 299.99f
        producto1.descuento = 15.5f
        producto1.mostrar_producto()
    }
}