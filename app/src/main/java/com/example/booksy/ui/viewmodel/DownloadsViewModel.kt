package com.example.booksy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksy.data.repository.BookRepository
import com.example.booksy.domain.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadsViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DownloadsUiState())
    val uiState: StateFlow<DownloadsUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadDownloadedBooks()
    }

    fun loadDownloadedBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                bookRepository.getDownloadedBooks().collect { books ->
                    _uiState.update { 
                        it.copy(
                            downloadedBooks = books,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar descargas: ${e.message}"
                    )
                }
            }
        }
    }

    fun searchDownloadedBooks(query: String) {
        _searchQuery.value = query
        
        if (query.isBlank()) {
            _uiState.update { it.copy(filteredBooks = emptyList()) }
            return
        }

        val filtered = _uiState.value.downloadedBooks.filter { book ->
            book.title.contains(query, ignoreCase = true) ||
            book.author.contains(query, ignoreCase = true) ||
            book.category.contains(query, ignoreCase = true)
        }
        
        _uiState.update { it.copy(filteredBooks = filtered) }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _uiState.update { it.copy(filteredBooks = emptyList()) }
    }

    fun removeDownloadedBook(bookId: String) {
        viewModelScope.launch {
            try {
                bookRepository.removeDownloadedBook(bookId)
                // Actualizar la lista local
                val updatedBooks = _uiState.value.downloadedBooks.filter { it.id != bookId }
                _uiState.update { 
                    it.copy(
                        downloadedBooks = updatedBooks,
                        filteredBooks = _uiState.value.filteredBooks.filter { it.id != bookId }
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = "Error al eliminar libro: ${e.message}"
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class DownloadsUiState(
    val downloadedBooks: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)