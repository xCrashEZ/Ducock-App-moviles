package com.example.booksy.data.local.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de Dagger Hilt para proporcionar dependencias de base de datos
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideBooksyDatabase(@ApplicationContext context: Context): BooksyDatabase {
        return Room.databaseBuilder(
            context,
            BooksyDatabase::class.java,
            BooksyDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
    }
    
    @Provides
    fun provideDownloadedBookDao(database: BooksyDatabase) = database.downloadedBookDao()
    
    @Provides
    fun provideUserProfileDao(database: BooksyDatabase) = database.userProfileDao()
}