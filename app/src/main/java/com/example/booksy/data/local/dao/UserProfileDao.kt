package com.example.booksy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksy.data.local.entities.UserProfileEntity

/**
 * DAO para gestionar el perfil de usuario localmente
 * Almacena información del perfil del usuario autenticado
 */
@Dao
interface UserProfileDao {
    
    /**
     * Obtener el perfil del usuario actual
     */
    @Query("SELECT * FROM user_profile LIMIT 1")
    suspend fun getUserProfile(): UserProfileEntity?
    
    /**
     * Insertar o actualizar el perfil del usuario
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfileEntity)
    
    /**
     * Actualizar la imagen de perfil
     */
    @Query("UPDATE user_profile SET profileImagePath = :imagePath WHERE id = :userId")
    suspend fun updateProfileImage(userId: String, imagePath: String?)
    
    /**
     * Eliminar el perfil del usuario (logout)
     */
    @Query("DELETE FROM user_profile")
    suspend fun clearUserProfile()
    
    /**
     * Verificar si hay un usuario autenticado
     */
    @Query("SELECT EXISTS(SELECT 1 FROM user_profile)")
    suspend fun hasUserProfile(): Boolean
}