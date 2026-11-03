package com.sarayasecas.login_compose.ui


import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun LogoCircular(
    @DrawableRes icono: Int,
    tamanoIcono: Dp,
) {

    Box(
        modifier = Modifier
            .size(72.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(icono),
            contentDescription = null,   // decorativo: los lectores de pantalla lo ignoran
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(tamanoIcono),
        )
    }
}