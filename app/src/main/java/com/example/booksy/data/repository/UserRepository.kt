package com.example.booksy.data.repository

import com.example.booksy.data.local.dao.UserProfileDao
import com.example.booksy.data.local.entities.UserProfileEntity
import com.example.booksy.data.local.preferences.AuthPreferences
import com.example.booksy.data.remote.api.BooksyApi
import com.example.booksy.data.remote.dto.LoginRequest
import com.example.booksy.data.remote.dto.SignupRequest
import com.example.booksy.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repositorio para gestionar autenticación y perfil de usuario
 * Maneja login, registro y gestión del perfil
 */
@Singleton
class UserRepository @Inject constructor(
    private val api: BooksyApi,
    private val authPreferences: AuthPreferences,
    private val userProfileDao: UserProfileDao
) {
    
    /**
     * Login de usuario
     */
    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = api.login(LoginRequest(email, password))
            
            if (response.isSuccessful) {
                val authResponse = response.body()
                authResponse?.let { auth ->
                    // Guardar token
                    authPreferences.saveAuthToken(auth.token)
                    
                    // Guardar información del usuario
                    authPreferences.saveUserInfo(
                        auth.user.id,
                        auth.user.name,
                        auth.user.email
                    )
                    
                    // Guardar en base de datos local
                    val userEntity = UserProfileEntity(
                        id = auth.user.id,
                        name = auth.user.name,
                        email = auth.user.email,
                        profileImagePath = auth.user.profileImage
                    )
                    userProfileDao.insertUserProfile(userEntity)
                    
                    Result.success(auth.user.toDomain())
                } ?: Result.failure(Exception("Respuesta inválida del servidor"))
            } else {
                Result.failure(Exception("Credenciales inválidas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Registro de usuario
     */
    suspend fun signup(name: String, email: String, password: String): Result<User> {
        return try {
            val response = api.signup(SignupRequest(name, email, password))
            
            if (response.isSuccessful) {
                val authResponse = response.body()
                authResponse?.let { auth ->
                    // Guardar token
                    authPreferences.saveAuthToken(auth.token)
                    
                    // Guardar información del usuario
                    authPreferences.saveUserInfo(
                        auth.user.id,
                        auth.user.name,
                        auth.user.email
                    )
                    
                    // Guardar en base de datos local
                    val userEntity = UserProfileEntity(
                        id = auth.user.id,
                        name = auth.user.name,
                        email = auth.user.email,
                        profileImagePath = auth.user.profileImage
                    )
                    userProfileDao.insertUserProfile(userEntity)
                    
                    Result.success(auth.user.toDomain())
                } ?: Result.failure(Exception("Respuesta inválida del servidor"))
            } else {
                Result.failure(Exception("Error en el registro"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Obtener perfil del usuario actual
     */
    suspend fun getUserProfile(): Result<User> {
        return try {
            // Primero intentar obtener de la API
            val token = authPreferences.getAuthToken()
            if (!token.isNullOrEmpty()) {
                val response = api.getUserProfile("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let { userDto ->
                        // Actualizar localmente
                        val userEntity = UserProfileEntity(
                            id = userDto.id,
                            name = userDto.name,
                            email = userDto.email,
                            profileImagePath = userDto.profileImage
                        )
                        userProfileDao.insertUserProfile(userEntity)
                        
                        return Result.success(userDto.toDomain())
                    }
                }
            }
            
            // Si falla la API, obtener de base de datos local
            val localProfile = userProfileDao.getUserProfile()
            if (localProfile != null) {
                Result.success(localProfile.toDomain())
            } else {
                Result.failure(Exception("No se pudo obtener el perfil del usuario"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Verificar si el usuario está autenticado
     */
    fun isUserAuthenticated(): Flow<Boolean> {
        return authPreferences.isUserAuthenticated()
    }
    
    /**
     * Actualizar imagen de perfil
     */
    suspend fun updateProfileImage(imagePath: String): Result<Unit> {
        return try {
            val userId = authPreferences.getUserInfo().let { flow ->
                var userId: String? = null
                flow.collect { (id, _, _) -> userId = id }
                userId
            }
            
            userId?.let {
                userProfileDao.updateProfileImage(it, imagePath)
                Result.success(Unit)
            } ?: Result.failure(Exception("Usuario no encontrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Cerrar sesión
     */
    suspend fun logout() {
        authPreferences.clearAuthData()
        userProfileDao.clearUserProfile()
    }
    
    /**
     * Convertir UserDto a modelo de dominio
     */
    private fun com.example.booksy.data.remote.dto.UserDto.toDomain(): User {
        return User(
            id = this.id,
            name = this.name,
            email = this.email,
            profileImage = this.profileImage,
            createdAt = this.createdAt
        )
    }
    
    /**
     * Convertir UserProfileEntity a modelo de dominio
     */
    private fun UserProfileEntity.toDomain(): User {
        return User(
            id = this.id,
            name = this.name,
            email = this.email,
            profileImage = this.profileImagePath,
            createdAt = this.lastSyncDate.toString()
        )
    }
}