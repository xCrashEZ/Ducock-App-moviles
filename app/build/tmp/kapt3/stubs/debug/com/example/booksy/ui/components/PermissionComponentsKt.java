package com.example.booksy.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a2\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a2\u0010\u0006\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001aU\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\u0002\b\u000fH\u0007\u001a]\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\u0002\b\u000f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u00a8\u0006\u0016"}, d2 = {"CameraPermissionDialog", "", "onPermissionGranted", "Lkotlin/Function0;", "onPermissionDenied", "onDismissRequest", "GalleryPermissionDialog", "PermissionAwareContent", "cameraPermissionGranted", "", "galleryPermissionGranted", "onCameraPermissionRequest", "onGalleryPermissionRequest", "onBothPermissionsGranted", "content", "Landroidx/compose/runtime/Composable;", "PermissionRequestDialog", "permission", "", "title", "message", "icon", "app_debug"})
public final class PermissionComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void PermissionRequestDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String permission, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> icon, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionGranted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionDenied, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismissRequest) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CameraPermissionDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionGranted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionDenied, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismissRequest) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GalleryPermissionDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionGranted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionDenied, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismissRequest) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PermissionAwareContent(boolean cameraPermissionGranted, boolean galleryPermissionGranted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCameraPermissionRequest, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onGalleryPermissionRequest, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBothPermissionsGranted, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
}