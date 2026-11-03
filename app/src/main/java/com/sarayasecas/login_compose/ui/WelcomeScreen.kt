package com.sarayasecas.login_compose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarayasecas.login_compose.R
import com.sarayasecas.login_compose.ui.theme.Login_composeTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


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


        LogoCircular(icono = R.drawable.ic_lock_open, tamanoIcono = 40.dp)

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = nombre,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(24.dp))


        Fireworks(modifier = Modifier.weight(1f))

        Spacer(Modifier.height(24.dp))

        OutlinedButton(
            onClick = onCerrarSesion,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
        ) {
            Text("Cerrar sesión")
        }

        Spacer(Modifier.height(16.dp))
    }
}


@Composable
private fun Fireworks(modifier: Modifier = Modifier) {

    val morado = MaterialTheme.colorScheme.primary
    val rosado = MaterialTheme.colorScheme.tertiary
    val gris = MaterialTheme.colorScheme.secondary

    Canvas(modifier = modifier.fillMaxWidth()) {

        val u = minOf(size.width / 312f, size.height / 400f)


        translate(left = (size.width - 312f * u) / 2f) {

            drawBurst(Offset(156 * u, 120 * u), 96 * u, 14, 5 * u, morado)
            drawBurst(Offset(64 * u, 268 * u), 52 * u, 10, 4 * u, rosado)
            drawBurst(Offset(256 * u, 306 * u), 48 * u, 9, 4 * u, gris)


            drawSparkle(Offset(40 * u, 40 * u), 11 * u, rosado)
            drawSparkle(Offset(280 * u, 34 * u), 13 * u, morado)
            drawSparkle(Offset(288 * u, 160 * u), 8 * u, gris)
            drawSparkle(Offset(24 * u, 150 * u), 9 * u, morado)
            drawSparkle(Offset(150 * u, 250 * u), 10 * u, gris)
            drawSparkle(Offset(160 * u, 380 * u), 7 * u, rosado)
        }
    }
}


private fun DrawScope.drawBurst(centro: Offset, radio: Float, rayos: Int, grosor: Float, color: Color) {
    val radioPunto = grosor * 0.9f

    for (i in 0 until rayos) {

        val angulo = 2 * PI * i / rayos - PI / 2
        val direccion = Offset(cos(angulo).toFloat(), sin(angulo).toFloat())
        val largo = if (i % 2 == 0) radio else radio * 0.72f

        drawLine(
            color = color,
            start = centro + direccion * (radio * 0.38f),
            end = centro + direccion * largo,
            strokeWidth = grosor,
            cap = StrokeCap.Round,
        )
        drawCircle(color, radioPunto, centro + direccion * (largo + radioPunto * 3.2f))
    }
    drawCircle(color, radioPunto * 1.4f, centro)
}


private fun DrawScope.drawSparkle(centro: Offset, medida: Float, color: Color) {
    val c = medida * 0.2f
    val estrella = Path().apply {
        moveTo(centro.x, centro.y - medida)
        lineTo(centro.x + c, centro.y - c)
        lineTo(centro.x + medida, centro.y)
        lineTo(centro.x + c, centro.y + c)
        lineTo(centro.x, centro.y + medida)
        lineTo(centro.x - c, centro.y + c)
        lineTo(centro.x - medida, centro.y)
        lineTo(centro.x - c, centro.y - c)
        close()
    }
    drawPath(estrella, color)
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

