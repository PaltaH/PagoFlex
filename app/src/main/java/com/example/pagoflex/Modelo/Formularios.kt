package com.example.pagoflex.Modelo

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class Rol {
    USUARIO,
    EJECUTIVO
}

data class Sesion(
    val correo: String,
    val nombre: String,
    val rol: Rol,
    val empresa: String,
    val avatar: ByteArray? = null
)

data class Compromiso(
    val id: String,
    val correo: String,
    val cliente: String,
    val empresa: String,
    val nombreEmpresa: String,
    val titulo: String,
    val monto: Long,
    val vencimiento: String,
    val anulado: Boolean = false,
    val motivoAnulacion: String = ""
)

data class Pago(
    val id: String,
    val idCompromiso: String,
    val fecha: String,
    val medio: String
)

data class DatosLocales(
    val compromisos: List<Compromiso> = emptyList(),
    val pagos: List<Pago> = emptyList()
)

enum class EstadoCompromiso(val etiqueta: String) {
    PENDIENTE("Pendiente"),
    VENCIDO("Vencido"),
    PAGADO("Pagado"),
    ANULADO("Anulado")
}

fun Compromiso.estadoCompromiso(pagos: List<Pago>): EstadoCompromiso {
    return when {
        anulado -> EstadoCompromiso.ANULADO
        pagos.any { it.idCompromiso == id } -> EstadoCompromiso.PAGADO
        LocalDate.parse(vencimiento).isBefore(LocalDate.now()) -> EstadoCompromiso.VENCIDO
        else -> EstadoCompromiso.PENDIENTE
    }
}

fun Compromiso.admiteCambios(pagos: List<Pago>): Boolean {
    val estado = estadoCompromiso(pagos)
    return estado == EstadoCompromiso.PENDIENTE || estado == EstadoCompromiso.VENCIDO
}

fun formatearMonto(monto: Long): String {
    val formato = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))
    formato.maximumFractionDigits = 0
    return formato.format(monto)
}

fun formatearFecha(fecha: String): String {
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.parse(fecha).format(formato)
}




