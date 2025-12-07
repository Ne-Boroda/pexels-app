package com.example.pexelsapp.domain.model

data class Photo(
    val id: Int,
    val photographer: String,
    val photographerUrl: String?,
    val url: String,
    val src: PhotoSrc,
    val avgColor: String?,
    val alt: String?,
    val isBookmarked: Boolean = false,
    val width: Int = 0,
    val height: Int = 0
)

data class PhotoSrc(
    val original: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)

data class PhotoList(
    val photos: List<Photo>,
    val page: Int,
    val totalResults: Int,
    val hasNextPage: Boolean
)