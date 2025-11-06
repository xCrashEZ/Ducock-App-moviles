package com.example.booksy.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad Room para libros descargados
 * Almacena información de libros disponibles offline
 */
@Entity(tableName = "downloaded_books")
data class DownloadedBookEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val coverImage: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val publicationDate: String,
    val pages: Int,
    val downloadDate: Long = System.currentTimeMillis(),
    val localFilePath: String? = null
)