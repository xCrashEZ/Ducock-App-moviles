package com.example.booksy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksy.data.repository.UserRepository
import com.example.booksy.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                val result = userRepository.getUserProfile()
                result.onSuccess { user ->
                    _uiState.update { 
                        it.copy(
                            user = user,
                            isLoading = false,
                            error = null
                        )
                    }
                }.onFailure { exception ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            error = "Error al cargar perfil: ${exception.message}"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar perfil: ${e.message}"
                    )
                }
            }
        }
    }

    fun updateProfileImage(imageUri: String) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isUpdatingImage = true) }
                userRepository.updateProfileImage(imageUri)
                
                // Recargar el perfil después de actualizar
                val result = userRepository.getUserProfile()
                result.onSuccess { updatedUser ->
                    _uiState.update { 
                        it.copy(
                            user = updatedUser,
                            isUpdatingImage = false
                        )
                    }
                }.onFailure { exception ->
                    _uiState.update { 
                        it.copy(
                            isUpdatingImage = false,
                            error = "Error al actualizar imagen: ${exception.message}"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isUpdatingImage = false,
                        error = "Error al actualizar imagen: ${e.message}"
                    )
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                userRepository.logout()
                _uiState.update { 
                    it.copy(
                        user = null,
                        isLoggedOut = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = "Error al cerrar sesión: ${e.message}"
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class ProfileUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val isUpdatingImage: Boolean = false,
    val error: String? = null,
    val isLoggedOut: Boolean = false
)