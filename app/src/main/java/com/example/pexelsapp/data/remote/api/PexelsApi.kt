package com.example.pexelsapp.data.remote.api

import com.example.pexelsapp.data.remote.dto.PhotosResponseDto
import com.example.pexelsapp.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsApi {

    // Получение популярных фото
    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): PhotosResponseDto

    // Поиск картинок
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): PhotosResponseDto

    // Детальная информация
    @GET("photos/{id}")
    suspend fun getPhotoDetails(
        @Path("id") id: Int
    ): PhotoDto
}