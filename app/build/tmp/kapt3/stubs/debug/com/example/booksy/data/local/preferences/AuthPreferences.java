package com.example.booksy.data.local.preferences;

/**
 * DataStore para gestionar las preferencias de autenticación
 * Almacena el token de forma segura
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0086@\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0086@\u00a2\u0006\u0002\u0010\fJ$\u0010\u000e\u001a \u0012\u001c\u0012\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f0\u0006J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006J\u0016\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0014J&\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0019R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/booksy/data/local/preferences/AuthPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authToken", "Lkotlinx/coroutines/flow/Flow;", "", "getAuthToken", "()Lkotlinx/coroutines/flow/Flow;", "clearAuthData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserId", "getUserInfo", "Lkotlin/Triple;", "isUserAuthenticated", "", "saveAuthToken", "token", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveUserInfo", "userId", "userName", "userEmail", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class AuthPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.properties.ReadOnlyProperty<?, ?> dataStore$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> AUTH_TOKEN_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> USER_ID_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> USER_NAME_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> USER_EMAIL_KEY = null;
    
    /**
     * Obtener el token de autenticación
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> authToken = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.booksy.data.local.preferences.AuthPreferences.Companion Companion = null;
    
    @javax.inject.Inject()
    public AuthPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Obtener el token de autenticación
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getAuthToken() {
        return null;
    }
    
    /**
     * Obtener el token de forma suspendida
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAuthToken(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Guardar el token de autenticación
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveAuthToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Guardar información del usuario
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String userEmail, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Obtener información del usuario
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Triple<java.lang.String, java.lang.String, java.lang.String>> getUserInfo() {
        return null;
    }
    
    /**
     * Limpiar todos los datos de autenticación (logout)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAuthData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Verificar si el usuario está autenticado
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isUserAuthenticated() {
        return null;
    }
    
    /**
     * Obtener el ID del usuario actual
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R%\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lcom/example/booksy/data/local/preferences/AuthPreferences$Companion;", "", "()V", "AUTH_TOKEN_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "USER_EMAIL_KEY", "USER_ID_KEY", "USER_NAME_KEY", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "dataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getDataStore(android.content.Context $this$dataStore) {
            return null;
        }
    }
}