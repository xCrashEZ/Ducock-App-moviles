package com.example.booksy.ui.screens.bookdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.booksy.ui.components.*
import com.example.booksy.ui.viewmodel.BookDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    navController: NavController,
    bookId: String,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(bookId) {
        viewModel.loadBook(bookId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Libro") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Compartir */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Compartir")
                    }
                }
            )
        }
    ) { paddingValues ->
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
                        message = uiState.error ?: "Error al cargar libro",
                        onRetry = { viewModel.loadBook(bookId) }
                    )
                }
            }
            uiState.book != null -> {
                val book = uiState.book!!
                AnimatedCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                    // Imagen del libro
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = book.coverImage,
                            contentDescription = "Portada de ${book.title}",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                    
                    // Información del libro
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        // Título
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Autor
                        Text(
                            text = "por ${book.author}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Rating y categoría
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RatingStars(rating = book.rating.toFloat())
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "%.1f".format(book.rating),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            
                            AssistChip(
                                onClick = { },
                                label = { Text(book.category) }
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Descripción
                        Text(
                            text = "Descripción",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = book.description,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.2f
                        )
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        // Botones de acción
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Botón de descarga
                            AnimatedButton(
                                onClick = { 
                                    if (uiState.isDownloaded) {
                                        viewModel.removeDownloadedBook()
                                    } else {
                                        viewModel.downloadBook()
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                enabled = !uiState.isDownloading
                            ) {
                                if (uiState.isDownloading) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                } else {
                                    Icon(
                                        imageVector = if (uiState.isDownloaded) Icons.Default.Favorite else Icons.Default.Download,
                                        contentDescription = if (uiState.isDownloaded) "Eliminar descarga" else "Descargar",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = when {
                                        uiState.isDownloading -> "Descargando..."
                                        uiState.isDownloaded -> "Descargado"
                                        else -> "Descargar"
                                    }
                                )
                            }
                            
                            // Botón de favorito (placeholder)
                            OutlinedButton(
                                onClick = { /* TODO: Añadir a favoritos */ },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "Añadir a favoritos",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Favorito")
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }
            else -> {
                EmptyState(
                    message = "Libro no encontrado",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
