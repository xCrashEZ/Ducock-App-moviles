package com.example.booksy.ui.screens.profile

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.booksy.ui.components.*
import com.example.booksy.ui.viewmodel.ProfileViewModel
import com.example.booksy.utils.PermissionUtils
import com.example.booksy.utils.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var showCameraPermissionDialog by remember { mutableStateOf(false) }
    var showGalleryPermissionDialog by remember { mutableStateOf(false) }
    var showImageSourceDialog by remember { mutableStateOf(false) }

    // val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    // val galleryPermissionState = rememberPermissionState(PermissionUtils.getGalleryPermission())

    if (showCameraPermissionDialog) {
        CameraPermissionDialog(
            onPermissionGranted = { showImageSourceDialog = true },
            onPermissionDenied = { showCameraPermissionDialog = false },
            onDismissRequest = { showCameraPermissionDialog = false }
        )
    }

    if (showGalleryPermissionDialog) {
        GalleryPermissionDialog(
            onPermissionGranted = { showImageSourceDialog = true },
            onPermissionDenied = { showGalleryPermissionDialog = false },
            onDismissRequest = { showGalleryPermissionDialog = false }
        )
    }

    if (showImageSourceDialog) {
        ImageSourceDialog(
            onCameraSelected = { showImageSourceDialog = false },
            onGallerySelected = { showImageSourceDialog = false },
            onDismissRequest = { showImageSourceDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.logout() }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar sesión")
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
                        message = uiState.error ?: "Error al cargar perfil",
                        onRetry = viewModel::loadUserProfile
                    )
                }
            }
            uiState.user != null -> {
                val user = uiState.user!!
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
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    AsyncImage(
                                        model = user.profileImage,
                                        contentDescription = "Foto de perfil",
                                        modifier = Modifier
                                            .size(120.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )

                                    if (uiState.isUpdatingImage) {
                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .align(Alignment.Center),
                                            strokeWidth = 2.dp
                                        )
                                    } else {
                                        IconButton(
                                            onClick = {
                                                when {
                                                    !PermissionUtils.hasCameraPermission(context) && !PermissionUtils.hasGalleryPermission(context) -> {
                                                        showCameraPermissionDialog = true
                                                    }
                                                    !PermissionUtils.hasCameraPermission(context) -> {
                                                        showCameraPermissionDialog = true
                                                    }
                                                    !PermissionUtils.hasGalleryPermission(context) -> {
                                                        showGalleryPermissionDialog = true
                                                    }
                                                    else -> {
                                                        showImageSourceDialog = true
                                                    }
                                                }
                                            },
                                            modifier = Modifier
                                                .size(32.dp)
                                                .clip(CircleShape)
                                                .clip(MaterialTheme.shapes.small)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Editar foto",
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    AnimatedText(
                                        text = user.name,
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    AnimatedText(
                                        text = user.email,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                        )
                                    )
                                }
                            }
                        }

                        AnimatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    StatisticItem(value = "12", label = "Libros Leídos")
                                    StatisticItem(value = "8", label = "Descargados")
                                    StatisticItem(value = "4.5", label = "Rating Promedio")
                                }

                                HorizontalDivider()

                                ProfileInfoItem(label = "Miembro desde", value = "Enero 2024")
                                ProfileInfoItem(label = "Género favorito", value = "Ciencia Ficción")
                                ProfileInfoItem(label = "Libros esta semana", value = "3")
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AnimatedButton(
                                onClick = { /* TODO: Editar perfil */ },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Editar Perfil")
                            }

                            AnimatedButton(
                                onClick = { viewModel.logout() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Cerrar Sesión")
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
            else -> {
                EmptyState(
                    message = "No se pudo cargar el perfil",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun StatisticItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedText(
            text = value,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        AnimatedText(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        )
    }
}

@Composable
fun ProfileInfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedText(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        )
        AnimatedText(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}
