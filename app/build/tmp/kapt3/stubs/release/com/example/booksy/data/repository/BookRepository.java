package com.example.booksy.data.repository;

/**
 * Repositorio para gestionar libros
 * Maneja operaciones tanto locales como remotas
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0013J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0018\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u0013J\f\u0010\u001b\u001a\u00020\u000b*\u00020\u001cH\u0002J\f\u0010\u001b\u001a\u00020\u000b*\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001e"}, d2 = {"Lcom/example/booksy/data/repository/BookRepository;", "", "api", "Lcom/example/booksy/data/remote/api/BooksyApi;", "downloadedBookDao", "Lcom/example/booksy/data/local/dao/DownloadedBookDao;", "(Lcom/example/booksy/data/remote/api/BooksyApi;Lcom/example/booksy/data/local/dao/DownloadedBookDao;)V", "downloadBook", "Lkotlin/Result;", "", "book", "Lcom/example/booksy/domain/model/Book;", "downloadBook-gIAlu-s", "(Lcom/example/booksy/domain/model/Book;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBooksFromApi", "", "token", "", "getBooksFromApi-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDownloadedBooks", "Lkotlinx/coroutines/flow/Flow;", "isBookDownloaded", "", "bookId", "removeDownloadedBook", "removeDownloadedBook-gIAlu-s", "toDomain", "Lcom/example/booksy/data/local/entities/DownloadedBookEntity;", "Lcom/example/booksy/data/remote/dto/BookDto;", "app_release"})
public final class BookRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.remote.api.BooksyApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.booksy.data.local.dao.DownloadedBookDao downloadedBookDao = null;
    
    @javax.inject.Inject()
    public BookRepository(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.remote.api.BooksyApi api, @org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.dao.DownloadedBookDao downloadedBookDao) {
        super();
    }
    
    /**
     * Obtener libros descargados localmente
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.booksy.domain.model.Book>> getDownloadedBooks() {
        return null;
    }
    
    /**
     * Verificar si un libro está descargado
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isBookDownloaded(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Convertir BookDto a modelo de dominio
     */
    private final com.example.booksy.domain.model.Book toDomain(com.example.booksy.data.remote.dto.BookDto $this$toDomain) {
        return null;
    }
    
    /**
     * Convertir DownloadedBookEntity a modelo de dominio
     */
    private final com.example.booksy.domain.model.Book toDomain(com.example.booksy.data.local.entities.DownloadedBookEntity $this$toDomain) {
        return null;
    }
}