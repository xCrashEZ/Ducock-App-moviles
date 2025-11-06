package com.example.booksy.domain.model

/**
 * Modelo de dominio para Categoría
 * Representa una categoría de libros en la capa de dominio
 */
data class Category(
    val id: String,
    val name: String,
    val description: String,
    val color: String
)