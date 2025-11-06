package com.example.booksy.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.booksy.utils.*

@Composable
fun PermissionRequestDialog(
    permission: String,
    title: String,
    message: String,
    icon: @Composable () -> Unit,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val permissionState = rememberPermissionState(permission)
    
    LaunchedEffect(permissionState.status) {
        when (permissionState.status) {
            PermissionStatus.Granted -> {
                onPermissionGranted()
            }
            PermissionStatus.Denied -> {
                onPermissionDenied()
            }
            else -> {}
        }
    }
    
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(title) },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 16.dp)
                ) {
                    icon()
                }
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    when (permissionState.status) {
                        PermissionStatus.NotRequested -> permissionState.launchPermissionRequest()
                        PermissionStatus.Denied -> permissionState.launchPermissionRequest()
                        else -> onDismissRequest()
                    }
                }
            ) {
                Text(
                    when (permissionState.status) {
                        PermissionStatus.NotRequested -> "Conceder Permiso"
                        PermissionStatus.Denied -> "Reintentar"
                        else -> "Aceptar"
                    }
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun CameraPermissionDialog(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    onDismissRequest: () -> Unit
) {
    PermissionRequestDialog(
        permission = android.Manifest.permission.CAMERA,
        title = "Acceso a Cámara",
        message = "Necesitamos acceso a tu cámara para tomar fotos de tu perfil.",
        icon = {
            Icon(
                Icons.Default.CameraAlt,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        onPermissionGranted = onPermissionGranted,
        onPermissionDenied = onPermissionDenied,
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun GalleryPermissionDialog(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    onDismissRequest: () -> Unit
) {
    PermissionRequestDialog(
        permission = com.example.booksy.utils.PermissionUtils.getGalleryPermission(),
        title = "Acceso a Galería",
        message = "Necesitamos acceso a tu galería para seleccionar fotos de tu perfil.",
        icon = {
            Icon(
                Icons.Default.PhotoLibrary,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        onPermissionGranted = onPermissionGranted,
        onPermissionDenied = onPermissionDenied,
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun PermissionAwareContent(
    cameraPermissionGranted: Boolean,
    galleryPermissionGranted: Boolean,
    onCameraPermissionRequest: () -> Unit,
    onGalleryPermissionRequest: () -> Unit,
    onBothPermissionsGranted: () -> Unit,
    content: @Composable () -> Unit
) {
    if (cameraPermissionGranted && galleryPermissionGranted) {
        onBothPermissionsGranted()
        content()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Permisos Requeridos",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Para continuar, necesitamos acceso a tu cámara y galería.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (!cameraPermissionGranted) {
                    Button(
                        onClick = onCameraPermissionRequest,
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cámara")
                    }
                }
                
                if (!galleryPermissionGranted) {
                    Button(
                        onClick = onGalleryPermissionRequest,
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Icon(Icons.Default.PhotoLibrary, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Galería")
                    }
                }
            }
        }
    }
}