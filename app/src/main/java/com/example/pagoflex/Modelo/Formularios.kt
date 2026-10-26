package com.example.pagoflex.modelo

import java.time.LocalDate

data class FormularioAcceso(
    val correo: String = "",
    val contrasena: String = "",
    val errores: Map<String, String> = emptyMap()
)

data class FormularioCompromiso(
    val nombre: String = "",
    val correo: String = "",
    val titulo: String = "",
    val monto: String = "",
    val fecha: String = LocalDate.now().plusDays(7).toString(),
    val errores: Map<String, String> = emptyMap()
)

data class FormularioAnulacion(
    val motivo: String = "",
    val error: String? = null
)
