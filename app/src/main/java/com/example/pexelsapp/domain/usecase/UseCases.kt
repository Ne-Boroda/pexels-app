package com.example.pexelsapp.domain.usecase

import javax.inject.Inject

data class UseCases @Inject constructor(
    val getCuratedPhotos: GetCuratedPhotosUseCase,
    val searchPhotos: SearchPhotosUseCase,
    val toggleBookmark: ToggleBookmarkUseCase,
    val getBookmarks: GetBookmarksUseCase,
    val getPhotoDetails: GetPhotoDetailsUseCase
)