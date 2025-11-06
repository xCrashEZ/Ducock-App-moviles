package com.example.booksy.data.remote.interceptors

import com.example.booksy.data.local.preferences.AuthPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor para añadir el token de autenticación a las peticiones
 * Añade el header Authorization: Bearer {token} cuando está disponible
 */
class AuthInterceptor @Inject constructor(
    private val authPreferences: AuthPreferences
) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Obtener el token de forma asíncrona
        val token = runBlocking {
            authPreferences.getAuthToken()
        }
        
        // Si no hay token, continuar con la petición original
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }
        
        // Añadir el token al header
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        
        return chain.proceed(authenticatedRequest)
    }
}