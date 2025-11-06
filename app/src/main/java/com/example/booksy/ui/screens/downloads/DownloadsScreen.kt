package com.example.booksy.ui.screens.downloads

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booksy.ui.components.*
import com.example.booksy.ui.viewmodel.DownloadsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadsScreen(
    navController: NavController,
    viewModel: DownloadsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Descargas") },
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
            // Barra de búsqueda
            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::searchDownloadedBooks,
                onSearch = viewModel::searchDownloadedBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = "Buscar en descargas..."
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
                            message = uiState.error ?: "Error al cargar descargas",
                            onRetry = viewModel::loadDownloadedBooks
                        )
                    }
                }
                else -> {
                    val booksToShow = if (searchQuery.isNotBlank() && uiState.filteredBooks.isNotEmpty()) {
                        uiState.filteredBooks
                    } else if (searchQuery.isBlank()) {
                        uiState.downloadedBooks
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
                            message = "No tienes libros descargados",
                            modifier = Modifier.fillMaxSize(),
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp),
                                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                                )
                            }
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                items = booksToShow,
                                key = { it.id }
                            ) { book ->
                                DownloadedBookCard(
                                    book = book,
                                    onBookClick = { /* TODO: Abrir libro */ },
                                    onDeleteClick = { viewModel.removeDownloadedBook(book.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DownloadedBookCard(
    book: com.example.booksy.domain.model.Book,
    onBookClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedCard(
        onClick = onBookClick,
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            // Imagen del libro
            AsyncImage(
                model = book.coverImage,
                contentDescription = "Portada de ${book.title}",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Información del libro
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rating
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RatingStars(rating = book.rating.toFloat())
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "%.1f".format(book.rating),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    // Botón de eliminar
                    IconButton(
                        onClick = onDeleteClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar descarga"
                        )
                    }
                }
            }
        }
    }
}
