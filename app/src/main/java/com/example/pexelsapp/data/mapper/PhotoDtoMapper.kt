package com.example.pexelsapp.data.mapper

import com.example.pexelsapp.data.remote.dto.PhotoDto
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.domain.model.PhotoSrc

object PhotoDtoMapper {

    internal fun toDomain(dto: PhotoDto, isBookmarked: Boolean = false): Photo {
        return Photo(
            id = dto.id,
            photographer = dto.photographer,
            photographerUrl = dto.photographerUrl,
            url = dto.url,
            src = PhotoSrc(
                original = dto.src.original,
                large = dto.src.large,
                medium = dto.src.medium,
                small = dto.src.small,
                portrait = dto.src.portrait,
                landscape = dto.src.landscape,
                tiny = dto.src.tiny
            ),
            avgColor = dto.avgColor,
            alt = dto.alt,
            width = dto.width,
            height = dto.height,
            isBookmarked = isBookmarked
        )
    }

    internal fun toDomain(
        dtoList: List<PhotoDto>,
        isBookmarkedList: List<Int> = emptyList()
    ): List<Photo> {
        return dtoList.map { dto ->
            toDomain(dto, isBookmarked = isBookmarkedList.contains(dto.id))
        }
    }
}