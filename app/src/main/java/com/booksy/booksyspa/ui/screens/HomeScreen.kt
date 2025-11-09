@file:OptIn(ExperimentalMaterial3Api::class)
package com.booksy.booksyspa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.booksy.booksyspa.ui.profile.ProfileScreen
import com.booksy.booksyspa.ui.auth.LoginScreen
import com.booksy.booksyspa.ui.auth.RegisterScreen

sealed class AppRoute(val route: String, val label: String, val icon: ImageVector) {
    data object Home : AppRoute("home", "Inicio", Icons.Filled.Home)
    data object Profile : AppRoute("profile", "Perfil", Icons.Filled.Person)
    data object Settings : AppRoute("settings", "Ajustes", Icons.Filled.Settings)
    data object Login : AppRoute("login", "Login", Icons.Filled.Person)

    companion object {
        val bottomDestinations = listOf(Home, Profile, Settings)
    }
}

@Composable
fun HomeEntry(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScaffoldCompact(navController)
        WindowWidthSizeClass.Medium -> HomeScaffoldMedium(navController)
        WindowWidthSizeClass.Expanded -> HomeScaffoldExpanded(navController)
        else -> HomeScaffoldCompact(navController)
    }
}

@Composable
private fun HomeScaffoldCompact(navController: NavHostController) {
    var selectedIndex by remember { mutableStateOf(0) }
    val items = AppRoute.bottomDestinations

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Booksy SPA") })
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { inner ->
        Box(Modifier.padding(inner)) {
            AppNavGraph(navController)
        }
    }
}

@Composable
private fun HomeScaffoldMedium(navController: NavHostController) {
    val items = AppRoute.bottomDestinations
    var selectedRoute by remember { mutableStateOf(AppRoute.Home.route) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Booksy SPA") }) }
    ) { inner ->
        Row(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
        ) {
            NavigationRail(modifier = Modifier.padding(end = 8.dp)) {
                items.forEach { item ->
                    NavigationRailItem(
                        selected = selectedRoute == item.route,
                        onClick = {
                            selectedRoute = item.route
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
            VerticalDivider(modifier = Modifier.fillMaxHeight())
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()) {
                AppNavGraph(navController)
            }
        }
    }
}

@Composable
private fun HomeScaffoldExpanded(navController: NavHostController) {
    val items = AppRoute.bottomDestinations
    var selectedRoute by remember { mutableStateOf(AppRoute.Home.route) }

    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet {
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Booksy SPA",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(16.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.label) },
                        selected = selectedRoute == item.route,
                        onClick = {
                            selectedRoute = item.route
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Panel") }) }
        ) { inner ->
            Box(Modifier.padding(inner)) {
                AppNavGraph(navController)
            }
        }
    }
}

@Composable
private fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Home.route
    ) {
        composable(AppRoute.Home.route) { HomePage() }
        composable(AppRoute.Profile.route) { ProfileScreen() }
        composable(AppRoute.Settings.route) { SettingsPage(navController) }
        composable(AppRoute.Login.route) { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
    }
}

@Composable
private fun HomePage() {
    CenterContent(title = "Inicio", body = "Pantalla principal (Compact/Medium/Expanded).")
}

@Composable
private fun ProfilePage() {
    CenterContent(title = "Perfil", body = "Ejemplo de vista con TopBar/Nav según tamaño.")
}

@Composable
private fun SettingsPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ajustes", style = MaterialTheme.typography.headlineMedium)
        Text("Configura tu experiencia en Booksy SPA.")
        Button(onClick = { navController.navigate(AppRoute.Login.route) }) {
            Text("Iniciar sesión")
        }
        TextButton(onClick = { navController.navigate("register") }) { Text("Crear cuenta") }
    }
}

@Composable
private fun CenterContent(title: String, body: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(body, style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { }) {
            Text("Acción")
        }
    }
}

@Preview(name = "Compact - 360x800", widthDp = 360, heightDp = 800, showBackground = true)
@Composable
private fun PreviewHomeCompact() {
    val navController = rememberNavController()
    HomeScaffoldCompact(navController)
}

@Preview(name = "Medium - 600x800", widthDp = 600, heightDp = 800, showBackground = true)
@Composable
private fun PreviewHomeMedium() {
    val navController = rememberNavController()
    HomeScaffoldMedium(navController)
}

@Preview(name = "Expanded - 1000x800", widthDp = 1000, heightDp = 800, showBackground = true)
@Composable
private fun PreviewHomeExpanded() {
    val navController = rememberNavController()
    HomeScaffoldExpanded(navController)
}
