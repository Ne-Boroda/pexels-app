package com.example.pexelsapp.domain.model

data class Bookmark(
    val photoId: Int,
    val addedAt: Long = System.currentTimeMillis()
)