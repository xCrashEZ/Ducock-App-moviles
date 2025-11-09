package com.booksy.booksyspa.ui.profile

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.booksy.booksyspa.ui.components.ImagePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var showImagePicker by remember { mutableStateOf(false) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ -> }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempCameraUri != null) {
            viewModel.updateAvatar(tempCameraUri)
        }
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.updateAvatar(it) }
    }

    LaunchedEffect(Unit) {
        viewModel.loadUser(1)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.error ?: "",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadUser(1) }) { Text("Reintentar") }
                }
            }
            else -> {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(text = "Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Nombre", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = state.userName, style = MaterialTheme.typography.bodyLarge)
                        }
                    }

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Email", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = state.userEmail, style = MaterialTheme.typography.bodyLarge)
                        }
                    }

                    Box(
                        modifier = Modifier.size(120.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        if (state.avatarUri != null) {
                            AsyncImage(
                                model = state.avatarUri,
                                contentDescription = "Avatar del usuario",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .clickable { showImagePicker = true },
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { showImagePicker = true },
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary
                            ) {}
                        }
                        Surface(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { showImagePicker = true },
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surface,
                            shadowElevation = 2.dp
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = "Cambiar foto",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadUser(1) }) { Text("Refrescar") }
                }
            }
        }
    }

    if (showImagePicker) {
        ImagePickerDialog(
            onDismiss = { showImagePicker = false },
            onCameraClick = {
                showImagePicker = false
                val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                permissionsLauncher.launch(permissions)
                tempCameraUri = createImageUri(context)
                tempCameraUri?.let { takePictureLauncher.launch(it) }
            },
            onGalleryClick = {
                showImagePicker = false
                pickImageLauncher.launch("image/*")
            }
        )
    }
}

private fun createImageUri(context: Context): Uri? {
    val timeStamp = java.text.SimpleDateFormat("yyyyMMdd_HHmmss", java.util.Locale.getDefault()).format(java.util.Date())
    val imageFileName = "profile_avatar_${timeStamp}.jpg"
    val storageDir = context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
    return try {
        val imageFile = java.io.File(storageDir, imageFileName)
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", imageFile)
    } catch (e: Exception) {
        null
    }
}