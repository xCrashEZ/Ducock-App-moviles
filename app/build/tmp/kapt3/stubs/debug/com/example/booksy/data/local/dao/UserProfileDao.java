package com.example.booksy.data.local.dao;

/**
 * DAO para gestionar el perfil de usuario localmente
 * Almacena información del perfil del usuario autenticado
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ \u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/booksy/data/local/dao/UserProfileDao;", "", "clearUserProfile", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserProfile", "Lcom/example/booksy/data/local/entities/UserProfileEntity;", "hasUserProfile", "", "insertUserProfile", "profile", "(Lcom/example/booksy/data/local/entities/UserProfileEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfileImage", "userId", "", "imagePath", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface UserProfileDao {
    
    /**
     * Obtener el perfil del usuario actual
     */
    @androidx.room.Query(value = "SELECT * FROM user_profile LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.booksy.data.local.entities.UserProfileEntity> $completion);
    
    /**
     * Insertar o actualizar el perfil del usuario
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertUserProfile(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.entities.UserProfileEntity profile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Actualizar la imagen de perfil
     */
    @androidx.room.Query(value = "UPDATE user_profile SET profileImagePath = :imagePath WHERE id = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProfileImage(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.Nullable()
    java.lang.String imagePath, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Eliminar el perfil del usuario (logout)
     */
    @androidx.room.Query(value = "DELETE FROM user_profile")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearUserProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Verificar si hay un usuario autenticado
     */
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM user_profile)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object hasUserProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}