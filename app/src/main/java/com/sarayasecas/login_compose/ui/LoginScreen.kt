package com.sarayasecas.login_compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarayasecas.login_compose.R
import com.sarayasecas.login_compose.ui.theme.Login_composeTheme


private const val CORREO_CORRECTO = "sarayasecas@gmail.com"
private const val CLAVE_CORRECTA = "1234"
private const val NOMBRE_USUARIO = "Saray"


@Composable
fun LoginScreen(
    onLoginCorrecto: (nombre: String) -> Unit,
    modifier: Modifier = Modifier,
) {


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var hayError by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .imePadding(),
    ) {
        Spacer(Modifier.height(40.dp))

        LogoCircular(icono = R.drawable.ic_lock, tamanoIcono = 32.dp)
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Inicia sesión para continuar",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(32.dp))


        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                hayError = false
            },
            label = { Text("Correo electrónico") },
            singleLine = true,
            isError = hayError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                hayError = false
            },
            label = { Text("Contraseña") },
            singleLine = true,
            isError = hayError,
            supportingText = { if (hayError) Text("Correo o contraseña incorrectos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(8.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            TextButton(onClick = { /* pendiente: recuperar contraseña */ }) {
                Text("¿Olvidaste tu contraseña?")
            }
        }

        Spacer(Modifier.height(16.dp))


        Button(
            onClick = {
                if (email.trim() == CORREO_CORRECTO && password == CLAVE_CORRECTA) {
                    onLoginCorrecto(NOMBRE_USUARIO)   // ¡acertó! Avisamos y enviamos el nombre
                } else {
                    hayError = true                   // falló: mostramos el error
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Iniciar sesión")
        }

        Spacer(Modifier.height(24.dp))


        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(
                text = "o",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            HorizontalDivider(modifier = Modifier.weight(1f))
        }

        Spacer(Modifier.height(24.dp))


        OutlinedButton(
            onClick = { /* pendiente: iniciar sesión con Google */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
        ) {
            Text("Continuar con Google")
        }


        Spacer(Modifier.weight(1f))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "¿No tienes cuenta?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            TextButton(onClick = { /* pendiente: pantalla de registro */ }) {
                Text("Regístrate")
            }
        }

        Spacer(Modifier.height(16.dp))
    }
}


@Preview(name = "Galaxy S24", showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun LoginScreenPreview() {
    Login_composeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreen(onLoginCorrecto = {})
        }
    }
}