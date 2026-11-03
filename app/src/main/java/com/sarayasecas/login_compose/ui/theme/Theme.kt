package com.sarayasecas.login_compose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    tertiary = Tertiary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant,
    background = Surface,
    onBackground = OnSurface,
)

@Composable
fun Login_composeTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}