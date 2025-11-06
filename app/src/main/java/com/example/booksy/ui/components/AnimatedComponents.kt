package com.example.booksy.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.booksy.ui.animations.pulseAnimation
import com.example.booksy.ui.animations.rotationAnimation

// Botón animado con efecto de pulso
@Composable
fun AnimatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    pulseEnabled: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    val scale = if (pulseEnabled) {
        pulseAnimation().value
    } else {
        1f
    }
    
    Button(
        onClick = onClick,
        modifier = modifier.scale(scale),
        enabled = enabled
    ) {
        content()
    }
}

// Indicador de carga animado
@Composable
fun AnimatedLoadingIndicator(
    modifier: Modifier = Modifier,
    size: Int = 48
) {
    val rotation = rotationAnimation().value
    
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Cargando",
            modifier = Modifier
                .size(size.dp * 0.6f)
                .graphicsLayer { rotationZ = rotation },
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

// Tarjeta animada con entrada suave
@Composable
fun AnimatedCard(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
    ) {
        if (onClick != null) {
            Card(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                content()
            }
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                content()
            }
        }
    }
}

// Lista animada con entrada escalonada
@Composable
fun AnimatedListItem(
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(index * 100L)
        visible = true
    }
    
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + slideInHorizontally(initialOffsetX = { -it / 4 }),
        exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it / 4 })
    ) {
        content()
    }
}

// Texto animado con efecto de aparición letra por letra
@Composable
fun AnimatedText(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyLarge
) {
    var visibleText by remember { mutableStateOf("") }
    
    LaunchedEffect(text) {
        text.forEachIndexed { index, char ->
            visibleText = text.substring(0, index + 1)
            kotlinx.coroutines.delay(50)
        }
    }
    
    Text(
        text = visibleText,
        modifier = modifier,
        style = style
    )
}

// Contenedor animado para errores con entrada suave
@Composable
fun AnimatedErrorContainer(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = slideInVertically(initialOffsetY = { -it }) + expandVertically(),
        exit = slideOutVertically(targetOffsetY = { -it }) + shrinkVertically()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}

// Botón flotante animado
@Composable
fun AnimatedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = scaleIn() + fadeIn(),
        exit = scaleOut() + fadeOut()
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            content()
        }
    }
}
