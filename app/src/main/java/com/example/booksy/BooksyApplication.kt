package com.example.booksy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase principal de la aplicación
 * Habilita Hilt para la inyección de dependencias
 */
@HiltAndroidApp
class BooksyApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // Inicialización de la aplicación
    }
}