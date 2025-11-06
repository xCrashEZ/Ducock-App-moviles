package com.example.booksy.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booksy.ui.components.*
import com.example.booksy.ui.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    val authState by viewModel.authState.collectAsState()
    
    LaunchedEffect(authState.isAuthenticated) {
        if (authState.isAuthenticated) {
            onRegisterSuccess()
        }
    }
    
    AnimatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo o título
            AnimatedText(
                text = "Crear Cuenta",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            AnimatedText(
                text = "Únete a Booksy",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            )
            
            Spacer(modifier = Modifier.height(32.dp))
        
        // Campo de nombre
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = authState.error != null
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Campo de email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            isError = authState.error != null
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            isError = authState.error != null || (password != confirmPassword && confirmPassword.isNotEmpty())
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Campo de confirmar contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            isError = authState.error != null || (password != confirmPassword && confirmPassword.isNotEmpty())
        )
        
        // Mostrar error si existe
        authState.error?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedText(
                text = error,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
        
        // Validación de contraseñas
        if (password != confirmPassword && confirmPassword.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedText(
                text = "Las contraseñas no coinciden",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Botón de registro
        AnimatedButton(
            onClick = { viewModel.signup(name, email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = !authState.isLoading && 
                     name.isNotBlank() && 
                     email.isNotBlank() && 
                     password.isNotBlank() && 
                     password == confirmPassword
        ) {
            if (authState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text("Registrarse", fontSize = 16.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Enlace a login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedText("¿Ya tienes cuenta?")
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = onNavigateToLogin) {
                AnimatedText("Inicia sesión")
            }
        }
    }
    }
}