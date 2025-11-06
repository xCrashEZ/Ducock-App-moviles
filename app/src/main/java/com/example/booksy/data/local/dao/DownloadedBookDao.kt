package com.example.booksy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksy.data.local.entities.DownloadedBookEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para gestionar libros descargados localmente
 * Proporciona operaciones CRUD para libros descargados
 */
@Dao
interface DownloadedBookDao {
    
    /**
     * Obtener todos los libros descargados ordenados por fecha de descarga
     */
    @Query("SELECT * FROM downloaded_books ORDER BY downloadDate DESC")
    fun getAllDownloadedBooks(): Flow<List<DownloadedBookEntity>>
    
    /**
     * Obtener un libro específico por ID
     */
    @Query("SELECT * FROM downloaded_books WHERE id = :bookId")
    suspend fun getDownloadedBookById(bookId: String): DownloadedBookEntity?
    
    /**
     * Insertar un nuevo libro descargado
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedBook(book: DownloadedBookEntity)
    
    /**
     * Eliminar un libro descargado
     */
    @Delete
    suspend fun deleteDownloadedBook(book: DownloadedBookEntity)
    
    /**
     * Verificar si un libro está descargado
     */
    @Query("SELECT EXISTS(SELECT 1 FROM downloaded_books WHERE id = :bookId)")
    suspend fun isBookDownloaded(bookId: String): Boolean
    
    /**
     * Obtener el número total de libros descargados
     */
    @Query("SELECT COUNT(*) FROM downloaded_books")
    suspend fun getDownloadedBooksCount(): Int
    
    /**
     * Eliminar todos los libros descargados
     */
    @Query("DELETE FROM downloaded_books")
    suspend fun clearAllDownloadedBooks()
}