package com.booksy.booksyspa.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.booksy.booksyspa.data.remote.ApiService
import com.booksy.booksyspa.data.remote.RetrofitClient
import com.booksy.booksyspa.data.remote.dto.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class RegisterUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val api = RetrofitClient.create(application).create(ApiService::class.java)

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onFirstNameChange(v: String) { _uiState.value = _uiState.value.copy(firstName = v) }
    fun onLastNameChange(v: String) { _uiState.value = _uiState.value.copy(lastName = v) }
    fun onEmailChange(v: String) { _uiState.value = _uiState.value.copy(email = v) }
    fun onUsernameChange(v: String) { _uiState.value = _uiState.value.copy(username = v) }
    fun onPasswordChange(v: String) { _uiState.value = _uiState.value.copy(password = v) }

    fun register() {
        val s = _uiState.value
        if (s.firstName.isBlank() || s.lastName.isBlank() || s.email.isBlank() || s.username.isBlank() || s.password.isBlank()) {
            _uiState.value = s.copy(error = "Todos los campos son obligatorios")
            return
        }
        _uiState.value = s.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                api.addUser(RegisterRequest(s.firstName, s.lastName, s.email, s.username, s.password))
                _uiState.value = _uiState.value.copy(isLoading = false, success = true)
            } catch (e: HttpException) {
                val msg = when (e.code()) {
                    409 -> "El usuario ya existe"
                    400 -> "Datos inválidos o incompletos"
                    else -> "Error (${e.code()}) al registrar"
                }
                _uiState.value = _uiState.value.copy(isLoading = false, error = msg)
            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = "Sin conexión a internet")
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = "Error inesperado: ${e.message}")
            }
        }
    }
}