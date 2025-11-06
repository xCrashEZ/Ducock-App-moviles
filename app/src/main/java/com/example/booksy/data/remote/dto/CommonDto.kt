package com.example.booksy.data.remote.dto

/**
 * DTO genérico para respuestas de error
 */
data class ErrorResponse(
    val error: String,
    val message: String,
    val statusCode: Int
)

/**
 * DTO para respuesta genérica de éxito
 */
data class SuccessResponse(
    val success: Boolean,
    val message: String
)