package com.example.booksy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksy.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado de la autenticación
 */
data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    init {
        checkAuthenticationStatus()
    }
    
    /**
     * Verificar el estado de autenticación actual
     */
    private fun checkAuthenticationStatus() {
        viewModelScope.launch {
            userRepository.isUserAuthenticated().collect { isAuthenticated ->
                _authState.update { currentState ->
                    currentState.copy(isAuthenticated = isAuthenticated)
                }
            }
        }
    }
    
    /**
     * Login de usuario
     */
    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.update {
                it.copy(error = "Por favor complete todos los campos")
            }
            return
        }
        
        if (!isValidEmail(email)) {
            _authState.update {
                it.copy(error = "Por favor ingrese un email válido")
            }
            return
        }
        
        viewModelScope.launch {
            _authState.update { it.copy(isLoading = true, error = null) }
            
            userRepository.login(email, password)
                .onSuccess {
                    _authState.update { 
                        it.copy(
                            isLoading = false,
                            isAuthenticated = true,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    _authState.update { 
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Error al iniciar sesión"
                        )
                    }
                }
        }
    }
    
    /**
     * Registro de usuario
     */
    fun signup(name: String, email: String, password: String) {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            _authState.update {
                it.copy(error = "Por favor complete todos los campos")
            }
            return
        }
        
        if (!isValidEmail(email)) {
            _authState.update {
                it.copy(error = "Por favor ingrese un email válido")
            }
            return
        }
        
        if (password.length < 6) {
            _authState.update {
                it.copy(error = "La contraseña debe tener al menos 6 caracteres")
            }
            return
        }
        
        viewModelScope.launch {
            _authState.update { it.copy(isLoading = true, error = null) }
            
            userRepository.signup(name, email, password)
                .onSuccess {
                    _authState.update { 
                        it.copy(
                            isLoading = false,
                            isAuthenticated = true,
                            error = null
                        )
                    }
                }
                .onFailure { error ->
                    _authState.update { 
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Error al registrar usuario"
                        )
                    }
                }
        }
    }
    
    /**
     * Cerrar sesión
     */
    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            _authState.update { 
                it.copy(
                    isAuthenticated = false,
                    error = null
                )
            }
        }
    }
    
    /**
     * Limpiar errores
     */
    fun clearError() {
        _authState.update { it.copy(error = null) }
    }
    
    /**
     * Validar formato de email
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}