package com.sarayasecas.login_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sarayasecas.login_compose.ui.LoginScreen
import com.sarayasecas.login_compose.ui.WelcomeScreen
import com.sarayasecas.login_compose.ui.theme.Login_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Login_composeTheme {
                App()
            }
        }
    }
}


@Composable
private fun App() {
    var nombreUsuario by rememberSaveable { mutableStateOf<String?>(null) }


    val fondo = if (nombreUsuario == null) R.drawable.fondo_motos1 else R.drawable.fondo_motos2

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (nombreUsuario == null) MaterialTheme.colorScheme.background.copy(alpha = 0.55f)
                    else Color.Black.copy(alpha = 0.35f)
                ),
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            val nombre = nombreUsuario

            if (nombre == null) {
                LoginScreen(
                    onLoginCorrecto = { nombreUsuario = it },
                    modifier = modifier,
                )
            } else {
                WelcomeScreen(
                    nombre = nombre,
                    onCerrarSesion = { nombreUsuario = null },
                    modifier = modifier,
                )
            }
        }
    }
}
