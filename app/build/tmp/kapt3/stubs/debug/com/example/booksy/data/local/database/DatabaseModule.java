package com.example.booksy.data.local.database;

/**
 * Módulo de Dagger Hilt para proporcionar dependencias de base de datos
 */
@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\f"}, d2 = {"Lcom/example/booksy/data/local/database/DatabaseModule;", "", "()V", "provideBooksyDatabase", "Lcom/example/booksy/data/local/database/BooksyDatabase;", "context", "Landroid/content/Context;", "provideDownloadedBookDao", "Lcom/example/booksy/data/local/dao/DownloadedBookDao;", "database", "provideUserProfileDao", "Lcom/example/booksy/data/local/dao/UserProfileDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.booksy.data.local.database.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.booksy.data.local.database.BooksyDatabase provideBooksyDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.booksy.data.local.dao.DownloadedBookDao provideDownloadedBookDao(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.database.BooksyDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.booksy.data.local.dao.UserProfileDao provideUserProfileDao(@org.jetbrains.annotations.NotNull()
    com.example.booksy.data.local.database.BooksyDatabase database) {
        return null;
    }
}