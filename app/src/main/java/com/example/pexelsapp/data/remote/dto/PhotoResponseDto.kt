package com.example.pexelsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotosResponseDto(
    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("photos")
    val photos: List<PhotoDto>,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("next_page")
    val nextPage: String? = null,

    @SerializedName("prev_page")
    val prevPage: String? = null
)