package com.booksy.booksyspa.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController, vm: RegisterViewModel = viewModel()) {
    val state by vm.uiState.collectAsState()

    LaunchedEffect(state.success) {
        if (state.success) {
            // Tras registrar, navegar al login
            navController.navigate("login") { launchSingleTop = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = state.firstName, onValueChange = vm::onFirstNameChange, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = state.lastName, onValueChange = vm::onLastNameChange, label = { Text("Apellido") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = state.email, onValueChange = vm::onEmailChange, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = state.username, onValueChange = vm::onUsernameChange, label = { Text("Usuario") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = state.password, onValueChange = vm::onPasswordChange, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())

        if (state.error != null) {
            Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
        }

        Button(onClick = { vm.register() }, enabled = !state.isLoading, modifier = Modifier.fillMaxWidth()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(18.dp))
            } else {
                Text("Registrar")
            }
        }
        TextButton(onClick = { navController.navigate("login") }) { Text("Ya tengo cuenta") }
    }
}