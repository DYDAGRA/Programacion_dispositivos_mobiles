package com.example.restaurante.interfaz_grafica.mozo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Pedido.Pedido

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreCuentaScreen(

    pedido: Pedido,

    onCerrar: () -> Unit

) {

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Pre Cuenta")

                },

                navigationIcon = {

                    TextButton(

                        onClick = onCerrar

                    ) {

                        Text("←")

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)

        ) {

            Text(

                text = "Mesa ${pedido.mesa.numero}",

                style = MaterialTheme.typography.headlineSmall

            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(

                modifier = Modifier.weight(1f)

            ) {

                items(

                    pedido.obtenerItems()

                ) { item ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)

                    ) {

                        Row(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),

                            horizontalArrangement =
                                Arrangement.SpaceBetween

                        ) {

                            Column {

                                Text(item.producto.nombre)

                                Text("Cantidad: ${item.cantidad}")

                            }

                            Text(

                                "S/. ${
                                    "%.2f".format(
                                        item.calcularSubtotal()
                                    )
                                }"

                            )

                        }

                    }

                }

            }

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = "TOTAL",

                style = MaterialTheme.typography.titleLarge

            )

            Text(

                text =

                    "S/. ${
                        "%.2f".format(
                            pedido.calcularTotal()
                        )
                    }",

                style = MaterialTheme.typography.headlineMedium

            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                onClick = {

                    onCerrar()

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Cerrar")

            }

        }

    }

}