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
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                bookRepository.getBooksFromApi().onSuccess { books ->
                    _uiState.update { 
                        it.copy(
                            books = books,
                            isLoading = false,
                            error = null
                        )
                    }
                }.onFailure { exception ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            error = "Error al cargar libros: ${exception.message}"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar libros: ${e.message}"
                    )
                }
            }
        }
    }

    fun searchBooks(query: String) {
        _searchQuery.value = query
        
        if (query.isBlank()) {
            _uiState.update { it.copy(filteredBooks = emptyList()) }
            return
        }

        val filtered = _uiState.value.books.filter { book ->
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

    fun refreshBooks() {
        loadBooks()
    }
}

data class HomeUiState(
    val books: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)