package com.example.pexelsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val id: Int,

    val photographer: String,

    @ColumnInfo(name = "photographer_url")
    val photographerUrl: String?,

    val url: String,

    // Разные размеры
    @ColumnInfo(name = "original_url")
    val originalUrl: String,

    @ColumnInfo(name = "large_url")
    val largeUrl: String,

    @ColumnInfo(name = "medium_url")
    val mediumUrl: String,

    @ColumnInfo(name = "small_url")
    val smallUrl: String,

    @ColumnInfo(name = "portrait_url")
    val portraitUrl: String,

    @ColumnInfo(name = "landscape_url")
    val landscapeUrl: String,

    @ColumnInfo(name = "tiny_url")
    val tinyUrl: String,

    // Дополнительные поля
    @ColumnInfo(name = "avg_color")
    val avgColor: String?,

    val alt: String?,

    val width: Int,
    val height: Int,

    // Метаданные
    @ColumnInfo(name = "added_at")
    val addedAt: Long = System.currentTimeMillis()
)