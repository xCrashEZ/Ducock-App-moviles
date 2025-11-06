package com.example.booksy.domain.model

/**
 * Modelo de dominio para Autor
 * Representa un autor en la capa de dominio
 */
data class Author(
    val id: String,
    val name: String,
    val biography: String,
    val photo: String,
    val birthDate: String,
    val nationality: String
)