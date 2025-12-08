package com.example.pexelsapp.ui

import androidx.compose.runtime.Composable
import com.example.pexelsapp.ui.navigation.AppNavHost
import com.example.pexelsapp.ui.theme.PexelsAppTheme

@Composable
fun App() {
    PexelsAppTheme {
        AppNavHost()
    }
}