package com.example.booksy.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad Room para el perfil de usuario
 * Almacena información del perfil localmente
 */
@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val profileImagePath: String? = null,
    val lastSyncDate: Long = System.currentTimeMillis()
)