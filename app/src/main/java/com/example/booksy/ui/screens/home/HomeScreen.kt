package com.example.booksy.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booksy.ui.components.*
import com.example.booksy.ui.navigation.Screen
import com.example.booksy.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Booksy") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Search.route) }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil")
                    }
                    IconButton(onClick = { navController.navigate(Screen.Downloads.route) }) {
                        Icon(Icons.Default.Download, contentDescription = "Descargas")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Barra de búsqueda
            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::searchBooks,
                onSearch = viewModel::searchBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            // Contenido principal
            when {
                uiState.isLoading -> {
                    AnimatedLoadingIndicator(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                uiState.error != null -> {
                    AnimatedErrorContainer(
                        visible = true,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ErrorMessage(
                            message = uiState.error ?: "Error al cargar libros",
                            onRetry = viewModel::refreshBooks
                        )
                    }
                }
                else -> {
                    val booksToShow = if (searchQuery.isNotBlank() && uiState.filteredBooks.isNotEmpty()) {
                        uiState.filteredBooks
                    } else if (searchQuery.isBlank()) {
                        uiState.books
                    } else {
                        emptyList()
                    }
                    
                    if (booksToShow.isEmpty() && searchQuery.isNotBlank()) {
                        EmptyState(
                            message = "No se encontraron libros para \"$searchQuery\"",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else if (booksToShow.isEmpty()) {
                        EmptyState(
                            message = "No hay libros disponibles",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            itemsIndexed(booksToShow) { index, book ->
                                AnimatedListItem(
                                    index = index,
                                    content = {
                                        BookCard(
                                            book = book,
                                            onBookClick = {
                                                navController.navigate(Screen.BookDetail.createRoute(book.id))
                                            }
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
