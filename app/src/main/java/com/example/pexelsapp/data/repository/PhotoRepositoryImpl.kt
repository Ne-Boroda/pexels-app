package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.local.dao.BookmarkDao
import com.example.pexelsapp.data.mapper.BookmarkMapper
import com.example.pexelsapp.data.mapper.PhotoDtoMapper
import com.example.pexelsapp.data.network.NetworkMonitor
import com.example.pexelsapp.data.remote.api.PexelsApi
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: PexelsApi,
    private val dao: BookmarkDao,
    private val networkMonitor: NetworkMonitor
) : PhotoRepository {

    private val _networkStatus = MutableStateFlow(true)

    override val isOnline: Flow<Boolean> = networkMonitor.isOnline

    override suspend fun updateNetworkStatus(isConnected: Boolean) {
        _networkStatus.value = isConnected
    }

    override suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int
    ): List<Photo> {
        return try {
            // Получаем фото из API
            val response = api.getCuratedPhotos(page, perPage)

            // Получаем список ID избранных фото для установки флага
            val bookmarkedIds = dao.getAllIds()

            // Преобразуем DTO в Domain модели с правильным флагом isBookmarked
            response.photos.map { dto ->
                PhotoDtoMapper.toDomain(dto, isBookmarked = bookmarkedIds.contains(dto.id))
            }
        } catch (e: Exception) {
            // Логируем ошибку и возвращаем пустой список
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): List<Photo> {
        return try {
            val response = api.searchPhotos(query, page, perPage)
            val bookmarkedIds = dao.getAllIds()

            response.photos.map { dto ->
                PhotoDtoMapper.toDomain(dto, isBookmarked = bookmarkedIds.contains(dto.id))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getPhotoDetails(id: Int): Photo {
        return try {
            val dto = api.getPhotoDetails(id)
            val isBookmarked = dao.exists(id)

            PhotoDtoMapper.toDomain(dto, isBookmarked = isBookmarked)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e // Пробрасываем дальше для обработки в ViewModel
        }
    }

    override fun getBookmarks(): Flow<List<Photo>> {
        return dao.getAll().map { entities ->
            entities.map { BookmarkMapper.toDomain(it) }
        }
    }

    override suspend fun addBookmark(photo: Photo) {
        val entity = BookmarkMapper.toEntity(photo)
        dao.insert(entity)
    }

    override suspend fun removeBookmark(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        return dao.exists(id)
    }

    override suspend fun toggleBookmark(photo: Photo) {
        if (dao.exists(photo.id)) {
            dao.deleteById(photo.id)
        } else {
            val entity = BookmarkMapper.toEntity(photo)
            dao.insert(entity)
        }
    }
}