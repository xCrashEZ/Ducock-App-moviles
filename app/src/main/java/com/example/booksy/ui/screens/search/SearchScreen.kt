package com.example.booksy.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booksy.ui.components.*
import com.example.booksy.ui.navigation.Screen
import com.example.booksy.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Libros") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
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
            // Barra de búsqueda destacada
            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::searchBooks,
                onSearch = viewModel::searchBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )
            
            // Resultados de búsqueda
            when {
                uiState.isLoading -> {
                    FullScreenLoading(
                        message = "Buscando libros..."
                    )
                }
                uiState.error != null -> {
                    AnimatedErrorContainer(
                        visible = true,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ErrorMessage(
                            message = uiState.error ?: "Error desconocido",
                            onRetry = viewModel::refreshBooks
                        )
                    }
                }
                else -> {
                    val booksToShow = if (searchQuery.isNotBlank()) {
                        uiState.filteredBooks
                    } else {
                        // Mostrar libros populares o recomendados cuando no hay búsqueda
                        uiState.books.take(10)
                    }
                    
                    if (searchQuery.isNotBlank() && booksToShow.isEmpty()) {
                        EmptyState(
                            message = "No se encontraron libros para \"$searchQuery\"",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else if (searchQuery.isBlank() && booksToShow.isEmpty()) {
                        EmptyState(
                            message = "Empieza a buscar libros",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            if (searchQuery.isBlank()) {
                                item {
                                    AnimatedText(
                                        text = "Libros Populares",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }
                            }
                            
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
