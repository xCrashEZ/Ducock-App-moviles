package com.booksy.booksyspa.repository

import android.content.Context
import com.booksy.booksyspa.data.remote.ApiService
import com.booksy.booksyspa.data.remote.RetrofitClient
import com.booksy.booksyspa.data.remote.dto.UserDto

class UserRepository(context: Context) {
    private val apiService: ApiService = RetrofitClient.create(context).create(ApiService::class.java)

    suspend fun fetchUser(id: Int = 1): Result<UserDto> {
        return try {
            val user = apiService.getUserById(id)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}