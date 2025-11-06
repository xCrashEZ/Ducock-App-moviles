package com.example.booksy.data.remote.api

import com.example.booksy.data.remote.dto.AuthResponse
import com.example.booksy.data.remote.dto.BooksResponse
import com.example.booksy.data.remote.dto.CreateOrderRequest
import com.example.booksy.data.remote.dto.LoginRequest
import com.example.booksy.data.remote.dto.OrderResponse
import com.example.booksy.data.remote.dto.SignupRequest
import com.example.booksy.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Interfaz de la API para Booksy
 * Define todos los endpoints disponibles
 */
interface BooksyApi {
    
    /**
     * Autenticación - Login de usuario
     */
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>
    
    /**
     * Autenticación - Registro de usuario
     */
    @POST("auth/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): Response<AuthResponse>
    
    /**
     * Obtener perfil del usuario autenticado
     */
    @GET("auth/me")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<UserDto>
    
    /**
     * Obtener lista de libros disponibles
     */
    @GET("libros")
    suspend fun getBooks(
        @Header("Authorization") token: String? = null
    ): Response<BooksResponse>
    
    /**
     * Crear un pedido/descarga de libro
     */
    @POST("pedidos")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body request: CreateOrderRequest
    ): Response<OrderResponse>
    
    companion object {
        const val BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW/"
    }
}