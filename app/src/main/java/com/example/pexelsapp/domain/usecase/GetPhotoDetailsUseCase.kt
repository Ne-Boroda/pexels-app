package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotoDetailsUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(id: Int): Photo {
        return repository.getPhotoDetails(id)
    }
}