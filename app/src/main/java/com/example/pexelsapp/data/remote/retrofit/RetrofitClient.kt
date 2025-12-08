package com.example.pexelsapp.data.remote.retrofit

import com.example.pexelsapp.BuildConfig
import com.example.pexelsapp.data.remote.api.PexelsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://api.pexels.com/v1/"
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pexelsApi: PexelsApi
        get() = retrofit.create(PexelsApi::class.java)

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(createAuthInterceptor())
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()

            val requestWithAuth = originalRequest.newBuilder()
                .header("Authorization", getApiKey())
                .build()

            chain.proceed(requestWithAuth)
        }
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun getApiKey(): String {
        val apiKey = BuildConfig.PEXELS_API_KEY
        if (apiKey.isEmpty()) {
            throw IllegalStateException("PEXELS_API_KEY is empty. Check local.properties")
        }
        return apiKey
    }
}