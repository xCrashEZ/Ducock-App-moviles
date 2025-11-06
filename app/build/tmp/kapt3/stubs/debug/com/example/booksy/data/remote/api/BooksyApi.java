package com.example.booksy.data.remote.api;

/**
 * Interfaz de la API para Booksy
 * Define todos los endpoints disponibles
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0017"}, d2 = {"Lcom/example/booksy/data/remote/api/BooksyApi;", "", "createOrder", "Lretrofit2/Response;", "Lcom/example/booksy/data/remote/dto/OrderResponse;", "token", "", "request", "Lcom/example/booksy/data/remote/dto/CreateOrderRequest;", "(Ljava/lang/String;Lcom/example/booksy/data/remote/dto/CreateOrderRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBooks", "Lcom/example/booksy/data/remote/dto/BooksResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserProfile", "Lcom/example/booksy/data/remote/dto/UserDto;", "login", "Lcom/example/booksy/data/remote/dto/AuthResponse;", "Lcom/example/booksy/data/remote/dto/LoginRequest;", "(Lcom/example/booksy/data/remote/dto/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signup", "Lcom/example/booksy/data/remote/dto/SignupRequest;", "(Lcom/example/booksy/data/remote/dto/SignupRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface BooksyApi {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW/";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.booksy.data.remote.api.BooksyApi.Companion Companion = null;
    
    /**
     * Autenticación - Login de usuario
     */
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.remote.dto.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.booksy.data.remote.dto.AuthResponse>> $completion);
    
    /**
     * Autenticación - Registro de usuario
     */
    @retrofit2.http.POST(value = "auth/signup")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object signup(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.remote.dto.SignupRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.booksy.data.remote.dto.AuthResponse>> $completion);
    
    /**
     * Obtener perfil del usuario autenticado
     */
    @retrofit2.http.GET(value = "auth/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserProfile(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.booksy.data.remote.dto.UserDto>> $completion);
    
    /**
     * Obtener lista de libros disponibles
     */
    @retrofit2.http.GET(value = "libros")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBooks(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.booksy.data.remote.dto.BooksResponse>> $completion);
    
    /**
     * Crear un pedido/descarga de libro
     */
    @retrofit2.http.POST(value = "pedidos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createOrder(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.remote.dto.CreateOrderRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.booksy.data.remote.dto.OrderResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/booksy/data/remote/api/BooksyApi$Companion;", "", "()V", "BASE_URL", "", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW/";
        
        private Companion() {
            super();
        }
    }
    
    /**
     * Interfaz de la API para Booksy
     * Define todos los endpoints disponibles
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}