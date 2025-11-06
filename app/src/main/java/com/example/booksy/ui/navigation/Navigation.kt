package com.example.booksy.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.booksy.ui.animations.ScreenTransitions
import com.example.booksy.ui.screens.auth.LoginScreen
import com.example.booksy.ui.screens.auth.RegisterScreen
import com.example.booksy.ui.screens.home.HomeScreen
import com.example.booksy.ui.screens.bookdetail.BookDetailScreen
import com.example.booksy.ui.screens.profile.ProfileScreen
import com.example.booksy.ui.screens.downloads.DownloadsScreen
import com.example.booksy.ui.screens.search.SearchScreen

/**
 * Graph de navegación principal de la aplicación
 */
@Composable
fun RootNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Screen.ROOT_GRAPH_ROUTE,
        startDestination = Screen.AUTH_GRAPH_ROUTE
    ) {
        authNavGraph(navController)
        mainNavGraph(navController)
    }
}

/**
 * Graph de navegación de autenticación
 */
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Login.route,
        route = Screen.AUTH_GRAPH_ROUTE
    ) {
        // Login
        composable(
            route = Screen.Login.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            LoginScreen(
                onLoginSuccess = {
                    navController.popBackStack()
                    navController.navigate(Screen.MAIN_GRAPH_ROUTE)
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        
        // Register
        composable(
            route = Screen.Register.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.popBackStack()
                    navController.navigate(Screen.MAIN_GRAPH_ROUTE)
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}

/**
 * Graph de navegación principal (después de autenticación)
 */
fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Home.route,
        route = Screen.MAIN_GRAPH_ROUTE
    ) {
        // Home
        composable(
            route = Screen.Home.route,
            enterTransition = { scaleIn(initialScale = 0.9f) },
            exitTransition = { fadeOut() }
        ) {
            HomeScreen(
                navController = navController
            )
        }
        
        // Book Detail
        composable(
            route = Screen.BookDetail.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookDetailScreen(
                navController = navController,
                bookId = bookId
            )
        }
        
        // Profile
        composable(
            route = Screen.Profile.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            ProfileScreen(
                navController = navController
            )
        }
        
        // Downloads
        composable(
            route = Screen.Downloads.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) {
            DownloadsScreen(
                navController = navController
            )
        }
        
        // Search
        composable(
            route = Screen.Search.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) }
        ) {
            SearchScreen(
                navController = navController
            )
        }
    }
}
