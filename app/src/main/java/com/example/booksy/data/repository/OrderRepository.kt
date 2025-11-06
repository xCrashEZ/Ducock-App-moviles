package com.example.booksy.data.repository

import com.example.booksy.data.local.preferences.AuthPreferences
import com.example.booksy.data.remote.api.BooksyApi
import com.example.booksy.data.remote.dto.CreateOrderRequest
import com.example.booksy.data.remote.dto.OrderResponse
import com.example.booksy.domain.model.Order
import com.example.booksy.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para gestionar pedidos/descargas
 * Maneva la creación y seguimiento de pedidos
 */
@Singleton
class OrderRepository @Inject constructor(
    private val api: BooksyApi,
    private val authPreferences: AuthPreferences
) {
    
    /**
     * Crear un nuevo pedido para descargar un libro
     */
    suspend fun createOrder(bookId: String): Result<Order> {
        return try {
            val token = authPreferences.getAuthToken()
            if (token.isNullOrEmpty()) {
                return Result.failure(Exception("Usuario no autenticado"))
            }
            
            val userId = authPreferences.getUserId()
            if (userId.isNullOrEmpty()) {
                return Result.failure(Exception("ID de usuario no encontrado"))
            }
            
            val response = api.createOrder(
                token = "Bearer $token",
                request = CreateOrderRequest(bookId, userId)
            )
            
            if (response.isSuccessful) {
                response.body()?.let { orderResponse ->
                    Result.success(orderResponse.toDomain())
                } ?: Result.failure(Exception("Respuesta inválida del servidor"))
            } else {
                Result.failure(Exception("Error al crear el pedido: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Obtener pedidos del usuario actual
     */
    fun getUserOrders(): Flow<Result<List<Order>>> = flow {
        try {
            // Por ahora simulamos los pedidos
            // En una implementación real, obtendríamos de la API
            val mockOrders = listOf(
                Order(
                    id = "1",
                    userId = "user1",
                    bookId = "book1",
                    orderDate = "2024-01-15",
                    status = OrderStatus.DOWNLOADED,
                    downloadUrl = "https://example.com/download/book1.pdf"
                ),
                Order(
                    id = "2",
                    userId = "user1",
                    bookId = "book2",
                    orderDate = "2024-01-10",
                    status = OrderStatus.CONFIRMED,
                    downloadUrl = "https://example.com/download/book2.pdf"
                )
            )
            emit(Result.success(mockOrders))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    
    /**
     * Convertir OrderResponse a modelo de dominio
     */
    private fun OrderResponse.toDomain(): Order {
        return Order(
            id = this.id,
            userId = this.userId,
            bookId = this.bookId,
            orderDate = this.orderDate,
            status = when (this.status.uppercase()) {
                "PENDING" -> OrderStatus.PENDING
                "CONFIRMED" -> OrderStatus.CONFIRMED
                "DOWNLOADED" -> OrderStatus.DOWNLOADED
                "CANCELLED" -> OrderStatus.CANCELLED
                else -> OrderStatus.PENDING
            },
            downloadUrl = this.downloadUrl
        )
    }
}
