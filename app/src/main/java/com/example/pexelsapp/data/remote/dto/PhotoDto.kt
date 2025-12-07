package com.example.pexelsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("photographer")
    val photographer: String,

    @SerializedName("photographer_url")
    val photographerUrl: String,

    @SerializedName("photographer_id")
    val photographerId: Int,

    @SerializedName("avg_color")
    val avgColor: String,

    @SerializedName("src")
    val src: PhotoSrcDto,

    @SerializedName("liked")
    val liked: Boolean,

    @SerializedName("alt")
    val alt: String
)