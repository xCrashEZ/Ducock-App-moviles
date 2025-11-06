package com.example.booksy.domain.model

/**
 * Modelo de dominio para Usuario
 * Representa un usuario/cliente en la capa de dominio
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImage: String? = null,
    val createdAt: String
)