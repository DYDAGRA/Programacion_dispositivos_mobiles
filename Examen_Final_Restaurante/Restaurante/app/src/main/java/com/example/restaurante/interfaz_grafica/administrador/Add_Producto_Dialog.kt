package com.example.restaurante.interfaz_grafica.administrador

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurante.model.Estados.Categoria
import com.example.restaurante.model.Productos.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductoDialog(

    onDismiss: () -> Unit,

    onSave: (Producto) -> Unit

) {

    var nombre by remember { mutableStateOf("") }

    var precio by remember { mutableStateOf("") }

    var stock by remember { mutableStateOf("") }

    var categoria by remember {

        mutableStateOf(Categoria.PLATOS)

    }

    var disponible by remember {

        mutableStateOf(true)

    }

    var expanded by remember {

        mutableStateOf(false)

    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text("Nuevo Producto")

        },

        text = {

            Column(

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                OutlinedTextField(

                    value = nombre,

                    onValueChange = {

                        nombre = it

                    },

                    label = {

                        Text("Nombre")

                    }

                )

                OutlinedTextField(

                    value = precio,

                    onValueChange = {

                        precio = it

                    },

                    label = {

                        Text("Precio")

                    }

                )

                OutlinedTextField(

                    value = stock,

                    onValueChange = {

                        stock = it

                    },

                    label = {

                        Text("Stock")

                    }

                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = categoria.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Categoría") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryEditable, true)  // ← Cambiado
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(

                        expanded = expanded,

                        onDismissRequest = {

                            expanded = false

                        }

                    ) {

                        Categoria.entries.forEach {

                            DropdownMenuItem(

                                text = {

                                    Text(it.name)

                                },

                                onClick = {

                                    categoria = it

                                    expanded = false

                                }

                            )

                        }

                    }

                }

                Row(

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Checkbox(

                        checked = disponible,

                        onCheckedChange = {

                            disponible = it

                        }

                    )

                    Text("Disponible")

                }

            }

        },

        confirmButton = {

            Button(

                onClick = {

                    onSave(

                        Producto(

                            nombre = nombre,

                            precio = precio.toDoubleOrNull() ?: 0.0,

                            categoria = categoria,

                            stock = stock.toIntOrNull() ?: 0,

                            disponible = disponible

                        )

                    )

                }

            ) {

                Text("Guardar")

            }

        },

        dismissButton = {

            TextButton(

                onClick = onDismiss

            ) {

                Text("Cancelar")

            }

        }

    )

}