package com.example.booksy.data.repository;

/**
 * Repositorio para gestionar pedidos/descargas
 * Maneva la creación y seguimiento de pedidos
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\b0\u000fJ\f\u0010\u0011\u001a\u00020\t*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0013"}, d2 = {"Lcom/example/booksy/data/repository/OrderRepository;", "", "api", "Lcom/example/booksy/data/remote/api/BooksyApi;", "authPreferences", "Lcom/example/booksy/data/local/preferences/AuthPreferences;", "(Lcom/example/booksy/data/remote/api/BooksyApi;Lcom/example/booksy/data/local/preferences/AuthPreferences;)V", "createOrder", "Lkotlin/Result;", "Lcom/example/booksy/domain/model/Order;", "bookId", "", "createOrder-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserOrders", "Lkotlinx/coroutines/flow/Flow;", "", "toDomain", "Lcom/example/booksy/data/remote/dto/OrderResponse;", "app_release"})
public final class OrderRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.remote.api.BooksyApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.local.preferences.AuthPreferences authPreferences = null;
    
    @javax.inject.Inject()
    public OrderRepository(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.remote.api.BooksyApi api, @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.preferences.AuthPreferences authPreferences) {
        super();
    }
    
    /**
     * Obtener pedidos del usuario actual
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Result<java.util.List<com.example.booksy.domain.model.Order>>> getUserOrders() {
        return null;
    }
    
    /**
     * Convertir OrderResponse a modelo de dominio
     */
    private final com.example.booksy.domain.model.Order toDomain(com.example.booksy.data.remote.dto.OrderResponse $this$toDomain) {
        return null;
    }
}