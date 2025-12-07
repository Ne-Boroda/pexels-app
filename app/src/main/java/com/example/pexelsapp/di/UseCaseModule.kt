package com.example.pexelsapp.di

import com.example.pexelsapp.domain.repository.PhotoRepository
import com.example.pexelsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCuratedPhotosUseCase(
        repository: PhotoRepository
    ): GetCuratedPhotosUseCase {
        return GetCuratedPhotosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSearchPhotosUseCase(
        repository: com.example.pexelsapp.domain.repository.PhotoRepository
    ): SearchPhotosUseCase {
        return SearchPhotosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(
        repository: com.example.pexelsapp.domain.repository.PhotoRepository
    ): ToggleBookmarkUseCase {
        return ToggleBookmarkUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetBookmarksUseCase(
        repository: com.example.pexelsapp.domain.repository.PhotoRepository
    ): GetBookmarksUseCase {
        return GetBookmarksUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPhotoDetailsUseCase(
        repository: com.example.pexelsapp.domain.repository.PhotoRepository
    ): GetPhotoDetailsUseCase {
        return GetPhotoDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        getCuratedPhotos: GetCuratedPhotosUseCase,
        searchPhotos: SearchPhotosUseCase,
        toggleBookmark: ToggleBookmarkUseCase,
        getBookmarks: GetBookmarksUseCase,
        getPhotoDetails: GetPhotoDetailsUseCase
    ): UseCases {
        return UseCases(
            getCuratedPhotos = getCuratedPhotos,
            searchPhotos = searchPhotos,
            toggleBookmark = toggleBookmark,
            getBookmarks = getBookmarks,
            getPhotoDetails = getPhotoDetails
        )
    }
}