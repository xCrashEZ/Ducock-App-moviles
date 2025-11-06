package com.example.booksy.ui.navigation

/**
 * Rutas de navegación de la aplicación
 */
sealed class Screen(val route: String) {
    // Autenticación
    object Login : Screen("login")
    object Register : Screen("register")
    
    // Principal
    object Home : Screen("home")
    object BookDetail : Screen("book_detail/{bookId}") {
        fun createRoute(bookId: String) = "book_detail/$bookId"
    }
    
    // Perfil y descargas
    object Profile : Screen("profile")
    object Downloads : Screen("downloads")
    
    // Búsqueda
    object Search : Screen("search")
    
    companion object {
        const val ROOT_GRAPH_ROUTE = "root"
        const val AUTH_GRAPH_ROUTE = "auth"
        const val MAIN_GRAPH_ROUTE = "main"
    }
}