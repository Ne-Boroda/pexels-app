package com.example.pexelsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pexelsapp.ui.screens.bookmarks.BookmarksScreen
import com.example.pexelsapp.ui.screens.home.HomeScreen
import com.example.pexelsapp.ui.screens.splash.SplashScreen
import com.example.pexelsapp.ui.viewmodel.SplashViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            val isLoading by viewModel.isLoading.collectAsState()

            SplashScreen()

            LaunchedEffect(isLoading) {
                if (!isLoading) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToBookmarks = {
                    navController.navigate(Screen.Bookmarks.route)
                }
            )
        }

        composable(Screen.Bookmarks.route) {
            BookmarksScreen(
                onNavigateToHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Search : Screen("search")
    object Bookmarks : Screen("bookmarks")
    object Details : Screen("details/{photoId}") {
        fun createRoute(photoId: Int) = "details/$photoId"
    }
}