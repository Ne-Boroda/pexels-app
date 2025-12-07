package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(): Flow<List<Photo>> {
        return repository.getBookmarks()
    }
}