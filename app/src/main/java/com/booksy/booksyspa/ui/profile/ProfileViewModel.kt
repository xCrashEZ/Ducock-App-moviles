package com.booksy.booksyspa.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.booksy.booksyspa.repository.UserRepository
import com.booksy.booksyspa.repository.AvatarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val userEmail: String = "",
    val error: String? = null,
    val avatarUri: Uri? = null
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(application)
    private val avatarRepository = AvatarRepository(application)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadSavedAvatar()
    }

    fun loadUser(id: Int = 1) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = repository.fetchUser(id)
            _uiState.value = result.fold(
                onSuccess = { user ->
                    ProfileUiState(
                        isLoading = false,
                        userName = listOfNotNull(user.firstName, user.lastName).joinToString(" "),
                        userEmail = user.email,
                        error = null
                    )
                },
                onFailure = { e ->
                    ProfileUiState(isLoading = false, error = e.message)
                }
            )
        }
    }

    private fun loadSavedAvatar() {
        viewModelScope.launch {
            avatarRepository.getAvatarUri().collect { savedUri ->
                _uiState.value = _uiState.value.copy(avatarUri = savedUri)
            }
        }
    }

    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch {
            avatarRepository.saveAvatarUri(uri)
        }
    }
}