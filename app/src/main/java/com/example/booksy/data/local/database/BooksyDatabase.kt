package com.example.booksy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksy.data.local.dao.DownloadedBookDao
import com.example.booksy.data.local.dao.UserProfileDao
import com.example.booksy.data.local.entities.DownloadedBookEntity
import com.example.booksy.data.local.entities.UserProfileEntity

/**
 * Base de datos Room para la aplicación Booksy
 * Almacena libros descargados y perfil de usuario localmente
 */
@Database(
    entities = [
        DownloadedBookEntity::class,
        UserProfileEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BooksyDatabase : RoomDatabase() {
    
    abstract fun downloadedBookDao(): DownloadedBookDao
    abstract fun userProfileDao(): UserProfileDao
    
    companion object {
        const val DATABASE_NAME = "booksy_database"
    }
}