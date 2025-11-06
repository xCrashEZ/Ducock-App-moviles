package com.example.booksy.data.local.dao;

/**
 * DAO para gestionar libros descargados localmente
 * Proporciona operaciones CRUD para libros descargados
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0015"}, d2 = {"Lcom/example/booksy/data/local/dao/DownloadedBookDao;", "", "clearAllDownloadedBooks", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDownloadedBook", "book", "Lcom/example/booksy/data/local/entities/DownloadedBookEntity;", "(Lcom/example/booksy/data/local/entities/DownloadedBookEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDownloadedBooks", "Lkotlinx/coroutines/flow/Flow;", "", "getDownloadedBookById", "bookId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDownloadedBooksCount", "", "insertDownloadedBook", "isBookDownloaded", "", "app_release"})
@androidx.room.Dao()
public abstract interface DownloadedBookDao {
    
    /**
     * Obtener todos los libros descargados ordenados por fecha de descarga
     */
    @androidx.room.Query(value = "SELECT * FROM downloaded_books ORDER BY downloadDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.booksy.data.local.entities.DownloadedBookEntity>> getAllDownloadedBooks();
    
    /**
     * Obtener un libro específico por ID
     */
    @androidx.room.Query(value = "SELECT * FROM downloaded_books WHERE id = :bookId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDownloadedBookById(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.booksy.data.local.entities.DownloadedBookEntity> $completion);
    
    /**
     * Insertar un nuevo libro descargado
     */
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDownloadedBook(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.entities.DownloadedBookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Eliminar un libro descargado
     */
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDownloadedBook(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.entities.DownloadedBookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Verificar si un libro está descargado
     */
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM downloaded_books WHERE id = :bookId)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isBookDownloaded(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    /**
     * Obtener el número total de libros descargados
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM downloaded_books")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDownloadedBooksCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Eliminar todos los libros descargados
     */
    @androidx.room.Query(value = "DELETE FROM downloaded_books")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAllDownloadedBooks(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}