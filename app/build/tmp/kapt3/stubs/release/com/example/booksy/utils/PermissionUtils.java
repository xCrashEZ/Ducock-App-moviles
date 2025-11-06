package com.example.booksy.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\n"}, d2 = {"Lcom/example/booksy/utils/PermissionUtils;", "", "()V", "getGalleryPermission", "", "hasCameraPermission", "", "context", "Landroid/content/Context;", "hasGalleryPermission", "app_release"})
public final class PermissionUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.booksy.utils.PermissionUtils INSTANCE = null;
    
    private PermissionUtils() {
        super();
    }
    
    public final boolean hasCameraPermission(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean hasGalleryPermission(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGalleryPermission() {
        return null;
    }
}