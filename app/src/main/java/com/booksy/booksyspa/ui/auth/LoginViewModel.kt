package com.booksy.booksyspa.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.booksy.booksyspa.data.local.SessionManager
import com.booksy.booksyspa.data.remote.ApiService
import com.booksy.booksyspa.data.remote.RetrofitClient
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient.create(application)
    private val api = retrofit.create(ApiService::class.java)
    private val sessionManager = SessionManager(application)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onUsernameChange(value: String) {
        _uiState.value = _uiState.value.copy(username = value)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun login() {
        val u = _uiState.value.username.trim()
        val p = _uiState.value.password.trim()
        if (u.isEmpty() || p.isEmpty()) {
            _uiState.value = _uiState.value.copy(error = "Usuario y contraseña son obligatorios")
            return
        }
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val resp = api.login(com.booksy.booksyspa.data.remote.dto.LoginRequest(u, p))
                sessionManager.saveAuthToken(resp.accessToken)
                _uiState.value = _uiState.value.copy(isLoading = false, success = true)
            } catch (e: HttpException) {
                val message = when (e.code()) {
                    400 -> "Datos inválidos o incompletos"
                    401, 404 -> "Usuario o contraseña incorrectos"
                    500 -> "Servidor temporalmente no disponible"
                    else -> "Error (${e.code()}): ${e.message()}"
                }
                _uiState.value = _uiState.value.copy(isLoading = false, error = message)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = "Sin conexión a internet")
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = "Error inesperado: ${e.message}")
            }
        }
    }
}