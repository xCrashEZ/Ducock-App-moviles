package com.example.booksy.data.remote.dto

/**
 * DTO para libro desde la API
 * Representa un libro en las respuestas del servidor
 */
data class BookDto(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val coverImage: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val publicationDate: String,
    val pages: Int,
    val isAvailable: Boolean = true
)

/**
 * DTO para respuesta de lista de libros
 */
data class BooksResponse(
    val books: List<BookDto>,
    val total: Int,
    val page: Int,
    val pageSize: Int
)

/**
 * DTO para crear un pedido
 */
data class CreateOrderRequest(
    val bookId: String,
    val userId: String
)

/**
 * DTO para respuesta de pedido
 */
data class OrderResponse(
    val id: String,
    val userId: String,
    val bookId: String,
    val orderDate: String,
    val status: String,
    val downloadUrl: String? = null
)