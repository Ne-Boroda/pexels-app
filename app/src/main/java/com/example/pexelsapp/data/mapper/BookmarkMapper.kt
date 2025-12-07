package com.example.pexelsapp.data.mapper

import com.example.pexelsapp.data.local.entity.BookmarkEntity
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.model.PhotoSrc

object BookmarkMapper {

    // Domain Photo > Room Entity
    fun toEntity(photo: Photo): BookmarkEntity {
        return BookmarkEntity(
            id = photo.id,
            photographer = photo.photographer,
            photographerUrl = photo.photographerUrl,
            url = photo.url,
            originalUrl = photo.src.original,
            largeUrl = photo.src.large,
            mediumUrl = photo.src.medium,
            smallUrl = photo.src.small,
            portraitUrl = photo.src.portrait ?: photo.src.original,
            landscapeUrl = photo.src.landscape ?: photo.src.original,
            tinyUrl = photo.src.tiny ?: photo.src.small,
            avgColor = photo.avgColor,
            alt = photo.alt,
            width = photo.width,
            height = photo.height
        )
    }

    // Room Entity > Domain Photo
    fun toDomain(entity: BookmarkEntity): Photo {
        return Photo(
            id = entity.id,
            photographer = entity.photographer,
            photographerUrl = entity.photographerUrl,
            url = entity.url,
            src = PhotoSrc(
                original = entity.originalUrl,
                large = entity.largeUrl,
                medium = entity.mediumUrl,
                small = entity.smallUrl,
                portrait = entity.portraitUrl,
                landscape = entity.landscapeUrl,
                tiny = entity.tinyUrl
            ),
            avgColor = entity.avgColor,
            alt = entity.alt,
            width = entity.width,
            height = entity.height,
            isBookmarked = true
        )
    }

    // Список Entity > Список Domain
    fun toDomain(entities: List<BookmarkEntity>): List<Photo> {
        return entities.map { toDomain(it) }
    }

    // Список Domain > Список Entity
    fun toEntity(photos: List<Photo>): List<BookmarkEntity> {
        return photos.map { toEntity(it) }
    }
}