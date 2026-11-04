package com.sarayasecas.login_compose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarayasecas.login_compose.R
import com.sarayasecas.login_compose.ui.theme.Login_composeTheme


@Composable
fun WelcomeScreen(
    nombre: String,
    onCerrarSesion: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))


        LogoCircular(icono = R.drawable.ic_moto, tamanoIcono = 40.dp)

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Listo para rodar",
            style = MaterialTheme.typography.headlineMedium.copy(shadow = sombraTexto),
            color = Color.White,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = nombre,
            style = MaterialTheme.typography.headlineMedium.copy(shadow = sombraTexto),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )

        Spacer(Modifier.height(24.dp))


        Spacer(Modifier.weight(1f))

        Spacer(Modifier.height(24.dp))

        Button(onClick = onCerrarSesion) {
            Text("Bajarse de la moto")
        }

        Spacer(Modifier.height(16.dp))
    }
}
private val sombraTexto = Shadow(color = Color.Black, offset = Offset(2f, 2f), blurRadius = 8f)

@Composable
private fun BanderaMeta(modifier: Modifier = Modifier) {

    val negro = MaterialTheme.colorScheme.secondary
    val rojo = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier.fillMaxWidth()) {

        val columnas = 6
        val filas = 4
        val lado = minOf(size.width * 0.7f / columnas, size.height * 0.5f / filas)
        val ancho = lado * columnas
        val alto = lado * filas
        val izquierda = (size.width - ancho) / 2f
        val arriba = (size.height - alto * 1.8f) / 2f

        drawLine(
            color = rojo,
            start = Offset(izquierda, arriba - lado * 0.3f),
            end = Offset(izquierda, arriba + alto * 1.8f),
            strokeWidth = 6.dp.toPx(),
            cap = StrokeCap.Round,
        )

        for (fila in 0 until filas) {
            for (columna in 0 until columnas) {
                drawRect(
                    color = if ((fila + columna) % 2 == 0) negro else Color.White,
                    topLeft = Offset(izquierda + columna * lado, arriba + fila * lado),
                    size = Size(lado, lado),
                )
            }
        }

        drawRect(
            color = negro,
            topLeft = Offset(izquierda, arriba),
            size = Size(ancho, alto),
            style = Stroke(width = 2.dp.toPx()),
        )
    }
}

@Preview(name = "Galaxy S24", showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun WelcomeScreenPreview() {
    Login_composeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeScreen(nombre = "Saray", onCerrarSesion = {})
        }
    }
}

