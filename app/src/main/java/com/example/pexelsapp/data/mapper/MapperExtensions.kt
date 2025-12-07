// data/mapper/MapperExtensions.kt
package com.example.pexelsapp.data.mapper

import com.example.pexelsapp.data.local.entity.BookmarkEntity
import com.example.pexelsapp.data.remote.dto.PhotoDto
import com.example.pexelsapp.domain.model.Photo

// DTO > Domain
fun PhotoDto.toDomain(isBookmarked: Boolean = false): Photo {
    return PhotoDtoMapper.toDomain(this, isBookmarked)
}

fun List<PhotoDto>.toDomain(isBookmarkedList: List<Int> = emptyList()): List<Photo> {
    return PhotoDtoMapper.toDomain(this, isBookmarkedList)
}

// Entity <> Domain
fun BookmarkEntity.toDomain(): Photo {
    return BookmarkMapper.toDomain(this)
}

fun List<BookmarkEntity>.toDomain(): List<Photo> {
    return BookmarkMapper.toDomain(this)
}

fun Photo.toEntity(): BookmarkEntity {
    return BookmarkMapper.toEntity(this)
}

fun List<Photo>.toEntity(): List<BookmarkEntity> {
    return BookmarkMapper.toEntity(this)
}