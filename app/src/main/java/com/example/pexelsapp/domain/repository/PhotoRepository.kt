package com.example.pexelsapp.domain.repository

import com.example.pexelsapp.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    // Удалённый доступ
    suspend fun getCuratedPhotos(
        page: Int = 1,
        perPage: Int = 30
    ): List<Photo>

    suspend fun searchPhotos(
        query: String,
        page: Int = 1,
        perPage: Int = 20
    ): List<Photo>

    suspend fun getPhotoDetails(id: Int): Photo

    // Локальное хранилище
    fun getBookmarks(): Flow<List<Photo>>

    suspend fun addBookmark(photo: Photo)

    suspend fun removeBookmark(id: Int)

    suspend fun isBookmarked(id: Int): Boolean

    // Вспомогательная функция для переключателя
    suspend fun toggleBookmark(photo: Photo) {
        if (isBookmarked(photo.id)) {
            removeBookmark(photo.id)
        } else {
            addBookmark(photo)
        }
    }

    // Статус сети
    val isOnline: Flow<Boolean>

    fun updateNetworkStatus(isConnected: Boolean)
}