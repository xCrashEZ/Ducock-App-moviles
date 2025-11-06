package com.example.booksy.domain.model

/**
 * Modelo de dominio para Pedido/Descarga
 * Representa un pedido de libro en la capa de dominio
 */
data class Order(
    val id: String,
    val userId: String,
    val bookId: String,
    val orderDate: String,
    val status: OrderStatus,
    val downloadUrl: String? = null
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    DOWNLOADED,
    CANCELLED
}