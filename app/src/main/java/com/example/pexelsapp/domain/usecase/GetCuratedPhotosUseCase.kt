package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import javax.inject.Inject

class GetCuratedPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(page: Int = 1): List<Photo> {
        return repository.getCuratedPhotos(page)
    }
}