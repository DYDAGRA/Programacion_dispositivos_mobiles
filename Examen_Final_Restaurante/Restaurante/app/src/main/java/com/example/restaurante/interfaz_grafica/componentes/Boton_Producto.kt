package com.example.restaurante.interfaz_grafica.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BotonProducto(

    texto: String,

    onClick: () -> Unit,

    modifier: Modifier = Modifier,

    enabled: Boolean = true,

    color: Color = MaterialTheme.colorScheme.primary

) {

    Button(

        onClick = onClick,

        modifier = modifier.fillMaxWidth(),

        enabled = enabled,

        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )

    ) {

        Text(texto)

    }

}