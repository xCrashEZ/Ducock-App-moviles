package com.example.booksy.data.repository;

/**
 * Repositorio para gestionar autenticación y perfil de usuario
 * Maneja login, registro y gestión del perfil
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\rJ4\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001cJ$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010\u001e\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010 J\f\u0010!\u001a\u00020\u000b*\u00020\"H\u0002J\f\u0010!\u001a\u00020\u000b*\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006$"}, d2 = {"Lcom/example/booksy/data/repository/UserRepository;", "", "api", "Lcom/example/booksy/data/remote/api/BooksyApi;", "authPreferences", "Lcom/example/booksy/data/local/preferences/AuthPreferences;", "userProfileDao", "Lcom/example/booksy/data/local/dao/UserProfileDao;", "(Lcom/example/booksy/data/remote/api/BooksyApi;Lcom/example/booksy/data/local/preferences/AuthPreferences;Lcom/example/booksy/data/local/dao/UserProfileDao;)V", "getUserProfile", "Lkotlin/Result;", "Lcom/example/booksy/domain/model/User;", "getUserProfile-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUserAuthenticated", "Lkotlinx/coroutines/flow/Flow;", "", "login", "email", "", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "signup", "name", "signup-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfileImage", "imagePath", "updateProfileImage-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/example/booksy/data/local/entities/UserProfileEntity;", "Lcom/example/booksy/data/remote/dto/UserDto;", "app_release"})
public final class UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.remote.api.BooksyApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.local.preferences.AuthPreferences authPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.local.dao.UserProfileDao userProfileDao = null;
    
    @javax.inject.Inject()
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.remote.api.BooksyApi api, @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.preferences.AuthPreferences authPreferences, @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.dao.UserProfileDao userProfileDao) {
        super();
    }
    
    /**
     * Verificar si el usuario está autenticado
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isUserAuthenticated() {
        return null;
    }
    
    /**
     * Cerrar sesión
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Convertir UserDto a modelo de dominio
     */
    private final com.example.booksy.domain.model.User toDomain(com.example.booksy.data.remote.dto.UserDto $this$toDomain) {
        return null;
    }
    
    /**
     * Convertir UserProfileEntity a modelo de dominio
     */
    private final com.example.booksy.domain.model.User toDomain(com.example.booksy.data.local.entities.UserProfileEntity $this$toDomain) {
        return null;
    }
}