package com.example.booksy.data.repository

import com.example.booksy.data.local.dao.DownloadedBookDao
import com.example.booksy.data.local.entities.DownloadedBookEntity
import com.example.booksy.data.remote.api.BooksyApi
import com.example.booksy.data.remote.dto.BookDto
import com.example.booksy.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para gestionar libros
 * Maneja operaciones tanto locales como remotas
 */
@Singleton
class BookRepository @Inject constructor(
    private val api: BooksyApi,
    private val downloadedBookDao: DownloadedBookDao
) {
    
    /**
     * Obtener todos los libros desde la API
     */
    suspend fun getBooksFromApi(token: String? = null): Result<List<Book>> {
        return try {
            val response = if (token != null) {
                api.getBooks("Bearer $token")
            } else {
                api.getBooks()
            }
            
            if (response.isSuccessful) {
                val books = response.body()?.books?.map { it.toDomain() } ?: emptyList()
                Result.success(books)
            } else {
                Result.failure(Exception("Error al obtener libros: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Obtener libros descargados localmente
     */
    fun getDownloadedBooks(): Flow<List<Book>> {
        return downloadedBookDao.getAllDownloadedBooks().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    /**
     * Descargar un libro (guardar localmente)
     */
    suspend fun downloadBook(book: Book): Result<Unit> {
        return try {
            val entity = DownloadedBookEntity(
                id = book.id,
                title = book.title,
                author = book.author,
                description = book.description,
                coverImage = book.coverImage,
                category = book.category,
                price = book.price,
                rating = book.rating,
                publicationDate = book.publicationDate,
                pages = book.pages,
                downloadDate = System.currentTimeMillis()
            )
            downloadedBookDao.insertDownloadedBook(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Verificar si un libro está descargado
     */
    suspend fun isBookDownloaded(bookId: String): Boolean {
        return downloadedBookDao.isBookDownloaded(bookId)
    }
    
    /**
     * Eliminar un libro descargado
     */
    suspend fun removeDownloadedBook(bookId: String): Result<Unit> {
        return try {
            val book = downloadedBookDao.getDownloadedBookById(bookId)
            book?.let {
                downloadedBookDao.deleteDownloadedBook(it)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Convertir BookDto a modelo de dominio
     */
    private fun BookDto.toDomain(): Book {
        return Book(
            id = this.id,
            title = this.title,
            author = this.author,
            description = this.description,
            coverImage = this.coverImage,
            category = this.category,
            price = this.price,
            rating = this.rating,
            publicationDate = this.publicationDate,
            pages = this.pages,
            isAvailable = this.isAvailable
        )
    }
    
    /**
     * Convertir DownloadedBookEntity a modelo de dominio
     */
    private fun DownloadedBookEntity.toDomain(): Book {
        return Book(
            id = this.id,
            title = this.title,
            author = this.author,
            description = this.description,
            coverImage = this.coverImage,
            category = this.category,
            price = this.price,
            rating = this.rating,
            publicationDate = this.publicationDate,
            pages = this.pages,
            isAvailable = true
        )
    }
}
