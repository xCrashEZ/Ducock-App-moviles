package com.example.booksy.domain.model

/**
 * Modelo de dominio para Libro
 * Representa un libro en la capa de dominio
 */
data class Book(
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