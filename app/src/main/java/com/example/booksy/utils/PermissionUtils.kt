package com.example.booksy.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

object PermissionUtils {
    
    fun hasCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    fun hasGalleryPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    fun getGalleryPermission(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    }
}

@Composable
fun rememberPermissionState(
    permission: String
): PermissionState {
    val context = LocalContext.current
    val permissionState = remember { mutableStateOf(PermissionStatus.Unknown) }
    
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionState.value = if (isGranted) {
                PermissionStatus.Granted
            } else {
                PermissionStatus.Denied
            }
        }
    )
    
    LaunchedEffect(Unit) {
        val isGranted = ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
        
        permissionState.value = if (isGranted) {
            PermissionStatus.Granted
        } else {
            PermissionStatus.NotRequested
        }
    }
    
    return PermissionState(
        status = permissionState.value,
        launchPermissionRequest = {
            launcher.launch(permission)
        }
    )
}

@Composable
fun rememberMultiplePermissionsState(
    permissions: List<String>
): MultiplePermissionsState {
    val context = LocalContext.current
    val permissionsState = remember { mutableStateOf<Map<String, PermissionStatus>>(emptyMap()) }
    
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { result ->
            val newState = result.mapValues { (_, isGranted) ->
                if (isGranted) PermissionStatus.Granted else PermissionStatus.Denied
            }
            permissionsState.value = newState
        }
    )
    
    LaunchedEffect(Unit) {
        val initialState = permissions.associateWith { permission ->
            val isGranted = ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
            
            if (isGranted) PermissionStatus.Granted else PermissionStatus.NotRequested
        }
        permissionsState.value = initialState
    }
    
    return MultiplePermissionsState(
        permissions = permissionsState.value,
        launchPermissionRequest = {
            launcher.launch(permissions.toTypedArray())
        },
        allPermissionsGranted = permissionsState.value.values.all { it == PermissionStatus.Granted }
    )
}

data class PermissionState(
    val status: PermissionStatus,
    val launchPermissionRequest: () -> Unit
)

data class MultiplePermissionsState(
    val permissions: Map<String, PermissionStatus>,
    val launchPermissionRequest: () -> Unit,
    val allPermissionsGranted: Boolean
)

enum class PermissionStatus {
    Granted,
    Denied,
    NotRequested,
    Unknown
}