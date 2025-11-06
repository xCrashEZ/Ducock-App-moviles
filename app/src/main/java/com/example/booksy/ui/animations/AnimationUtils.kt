@file:OptIn(ExperimentalAnimationApi::class)
package com.example.booksy.ui.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

// Animaciones de entrada/salida
object AnimationUtils {
    
    // Animación de fade in/out
    fun fadeInOut() = fadeIn(
        animationSpec = tween(durationMillis = 300)
    ) with fadeOut(
        animationSpec = tween(durationMillis = 300)
    )
    
    // Animación de slide desde abajo
    fun slideInFromBottom() = slideInVertically(
        animationSpec = tween(durationMillis = 300),
        initialOffsetY = { it }
    ) with slideOutVertically(
        animationSpec = tween(durationMillis = 300),
        targetOffsetY = { it }
    )
    
    // Animación de slide desde la derecha
    fun slideInFromRight() = slideInHorizontally(
        animationSpec = tween(durationMillis = 300),
        initialOffsetX = { it }
    ) with slideOutHorizontally(
        animationSpec = tween(durationMillis = 300),
        targetOffsetX = { it }
    )
    
    // Animación de slide desde la izquierda
    fun slideInFromLeft() = slideInHorizontally(
        animationSpec = tween(durationMillis = 300),
        initialOffsetX = { -it }
    ) with slideOutHorizontally(
        animationSpec = tween(durationMillis = 300),
        targetOffsetX = { -it }
    )
    
    // Animación de escala
    fun scaleInOut() = scaleIn(
        animationSpec = tween(durationMillis = 300),
        initialScale = 0.8f
    ) with scaleOut(
        animationSpec = tween(durationMillis = 300),
        targetScale = 0.8f
    )
    
    // Animación de expansión vertical
    fun expandVertically() = expandVertically(
        animationSpec = tween(durationMillis = 300),
        expandFrom = Alignment.Top
    ) with shrinkVertically(
        animationSpec = tween(durationMillis = 300),
        shrinkTowards = Alignment.Top
    )
    
    // Animación de expansión horizontal
    fun expandHorizontally() = expandHorizontally(
        animationSpec = tween(durationMillis = 300),
        expandFrom = Alignment.Start
    ) with shrinkHorizontally(
        animationSpec = tween(durationMillis = 300),
        shrinkTowards = Alignment.Start
    )
}

// Animaciones personalizadas para componentes
@Composable
fun AnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    androidx.compose.animation.AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
        content = content
    )
}

// Animación de pulso para botones
@Composable
fun pulseAnimation(): Animatable<Float, AnimationVector1D> {
    val pulseAnimation = remember { Animatable(1f) }
    
    LaunchedEffect(Unit) {
        pulseAnimation.animateTo(
            targetValue = 1.1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )
    }
    
    return pulseAnimation
}

// Animación de rotación
@Composable
fun rotationAnimation(): Animatable<Float, AnimationVector1D> {
    val rotationAnimation = remember { Animatable(0f) }
    
    LaunchedEffect(Unit) {
        rotationAnimation.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing)
            )
        )
    }
    
    return rotationAnimation
}

// Transiciones entre pantallas
object ScreenTransitions {
    
    // Transición suave entre pantallas
    val smoothTransition = slideInHorizontally(
        animationSpec = tween(durationMillis = 300),
        initialOffsetX = { it }
    ) + fadeIn(
        animationSpec = tween(durationMillis = 300)
    ) with slideOutHorizontally(
        animationSpec = tween(durationMillis = 300),
        targetOffsetX = { -it }
    ) + fadeOut(
        animationSpec = tween(durationMillis = 300)
    )
    
    // Transición de desvanecimiento
    val fadeTransition = fadeIn(
        animationSpec = tween(durationMillis = 500)
    ) with fadeOut(
        animationSpec = tween(durationMillis = 500)
    )
    
    // Transición de escala
    val scaleTransition = scaleIn(
        animationSpec = tween(durationMillis = 300),
        initialScale = 0.9f
    ) with scaleOut(
        animationSpec = tween(durationMillis = 300),
        targetScale = 1.1f
    )
}
