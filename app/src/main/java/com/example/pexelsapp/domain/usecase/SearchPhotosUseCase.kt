package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(query: String, page: Int = 1): List<Photo> {
        require(query.isNotBlank()) { "Query cannot be empty" }
        require(query.length >= 2) { "Query must be at least 2 characters" }
        return repository.searchPhotos(query, page)
    }
}