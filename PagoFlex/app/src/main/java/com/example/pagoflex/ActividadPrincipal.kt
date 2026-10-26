package com.example.pagoflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import com.example.pagoflex.interfaz.tema.TemaPagoFlex
import com.example.pagoflex.navegacion.NavegacionAplicacion

class ActividadPrincipal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TemaPagoFlex {
                NavegacionAplicacion()
            }
        }
    }
}

