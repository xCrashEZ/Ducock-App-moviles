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
class BookDetailViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState: StateFlow<BookDetailUiState> = _uiState.asStateFlow()

    fun loadBook(bookId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                // Primero buscamos en los libros descargados
                bookRepository.getDownloadedBooks().collect { downloadedBooks ->
                    val downloadedBook = downloadedBooks.find { it.id == bookId }
                    
                    if (downloadedBook != null) {
                        _uiState.update { 
                            it.copy(
                                book = downloadedBook,
                                isDownloaded = true,
                                isLoading = false,
                                error = null
                            )
                        }
                    } else {
                        // Si no está descargado, buscamos en la API
                        bookRepository.getBooksFromApi().onSuccess { books ->
                            val book = books.find { it.id == bookId }
                            
                            if (book != null) {
                                _uiState.update { 
                                    it.copy(
                                        book = book,
                                        isDownloaded = false,
                                        isLoading = false,
                                        error = null
                                    )
                                }
                            } else {
                                _uiState.update { 
                                    it.copy(
                                        isLoading = false,
                                        error = "Libro no encontrado"
                                    )
                                }
                            }
                        }.onFailure { exception ->
                            _uiState.update { 
                                it.copy(
                                    isLoading = false,
                                    error = "Error al cargar el libro: ${exception.message}"
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar el libro: ${e.message}"
                    )
                }
            }
        }
    }

    fun downloadBook() {
        viewModelScope.launch {
            _uiState.value.book?.let { book ->
                try {
                    _uiState.update { it.copy(isDownloading = true) }
                    bookRepository.downloadBook(book)
                    _uiState.update { 
                        it.copy(
                            isDownloaded = true,
                            isDownloading = false
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update { 
                        it.copy(
                            isDownloading = false,
                            error = "Error al descargar: ${e.message}"
                        )
                    }
                }
            }
        }
    }

    fun removeDownloadedBook() {
        viewModelScope.launch {
            _uiState.value.book?.let { book ->
                try {
                    bookRepository.removeDownloadedBook(book.id)
                    _uiState.update { 
                        it.copy(
                            isDownloaded = false
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update { 
                        it.copy(
                            error = "Error al eliminar descarga: ${e.message}"
                        )
                    }
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class BookDetailUiState(
    val book: Book? = null,
    val isLoading: Boolean = false,
    val isDownloading: Boolean = false,
    val isDownloaded: Boolean = false,
    val error: String? = null
)