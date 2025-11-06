package com.example.booksy.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * DataStore para gestionar las preferencias de autenticación
 * Almacena el token de forma segura
 */
@Singleton
class AuthPreferences @Inject constructor(
    private val context: Context
) {
    
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preferences")
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }
    
    /**
     * Obtener el token de autenticación
     */
    val authToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[AUTH_TOKEN_KEY]
        }
    
    /**
     * Obtener el token de forma suspendida
     */
    suspend fun getAuthToken(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[AUTH_TOKEN_KEY]
        }.let { flow ->
            var token: String? = null
            flow.collect { token = it }
            token
        }
    }
    
    /**
     * Guardar el token de autenticación
     */
    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
        }
    }
    
    /**
     * Guardar información del usuario
     */
    suspend fun saveUserInfo(userId: String, userName: String, userEmail: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_NAME_KEY] = userName
            preferences[USER_EMAIL_KEY] = userEmail
        }
    }
    
    /**
     * Obtener información del usuario
     */
    fun getUserInfo(): Flow<Triple<String?, String?, String?>> = context.dataStore.data
        .map { preferences ->
            Triple(
                preferences[USER_ID_KEY],
                preferences[USER_NAME_KEY],
                preferences[USER_EMAIL_KEY]
            )
        }
    
    /**
     * Limpiar todos los datos de autenticación (logout)
     */
    suspend fun clearAuthData() {
        context.dataStore.edit { preferences ->
            preferences.remove(AUTH_TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
            preferences.remove(USER_NAME_KEY)
            preferences.remove(USER_EMAIL_KEY)
        }
    }
    
    /**
     * Verificar si el usuario está autenticado
     */
    fun isUserAuthenticated(): Flow<Boolean> = authToken
        .map { token -> !token.isNullOrEmpty() }
    
    /**
     * Obtener el ID del usuario actual
     */
    suspend fun getUserId(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }.let { flow ->
            var userId: String? = null
            flow.collect { userId = it }
            userId
        }
    }
}