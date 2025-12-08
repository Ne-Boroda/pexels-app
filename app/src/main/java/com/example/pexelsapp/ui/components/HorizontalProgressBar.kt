package com.example.pexelsapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalProgressBar(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            progress = 0f
            while (progress < 0.9f) {
                progress += 0.1f
                kotlinx.coroutines.delay(100)
            }
        } else {
            progress = if (progress == 1f) 0f else 1f
            kotlinx.coroutines.delay(300)
            progress = 0f
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 300)
    )

    if (isLoading || progress > 0) {
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = modifier
                .fillMaxWidth()
                .height(2.dp),
            color = Color.Red
        )
    }
}