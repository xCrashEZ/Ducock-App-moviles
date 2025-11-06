package com.example.booksy.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.booksy.ui.theme.RatingStar

@Composable
fun RatingStars(
    rating: Float,
    modifier: Modifier = Modifier,
    starSize: Dp = 16.dp,
    filledColor: Color = RatingStar,
    unfilledColor: Color = Color.Gray.copy(alpha = 0.3f)
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            val isFilled = i <= rating
            val isHalfFilled = i > rating && i - 0.5f <= rating
            
            Icon(
                imageVector = when {
                    isFilled -> Icons.Filled.Star
                    isHalfFilled -> Icons.Filled.Star // Podrías usar un icono de media estrella aquí
                    else -> Icons.Outlined.Star
                },
                contentDescription = null,
                modifier = Modifier.size(starSize),
                tint = if (isFilled || isHalfFilled) filledColor else unfilledColor
            )
        }
    }
}