package com.example.booksy.data.remote.dto

/**
 * DTO para respuesta de autenticación
 * Contiene token y datos del usuario
 */
data class AuthResponse(
    val token: String,
    val user: UserDto
)

/**
 * DTO para datos del usuario
 */
data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val profileImage: String? = null,
    val createdAt: String
)

/**
 * DTO para login
 */
data class LoginRequest(
    val email: String,
    val password: String
)

/**
 * DTO para registro
 */
data class SignupRequest(
    val name: String,
    val email: String,
    val password: String
)