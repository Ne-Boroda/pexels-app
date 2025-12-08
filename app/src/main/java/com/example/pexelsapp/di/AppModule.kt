package com.example.pexelsapp.di

import android.content.Context
import androidx.room.Room
import com.example.pexelsapp.data.local.dao.BookmarkDao
import com.example.pexelsapp.data.local.database.AppDatabase
import com.example.pexelsapp.data.network.NetworkMonitor
import com.example.pexelsapp.data.remote.api.PexelsApi
import com.example.pexelsapp.data.remote.retrofit.RetrofitClient
import com.example.pexelsapp.data.repository.PhotoRepositoryImpl
import com.example.pexelsapp.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Отслеживание сети
    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor {
        return NetworkMonitor(context)
    }

    // Pexels API
    @Provides
    @Singleton
    fun providePexelsApi(
        retrofitClient: RetrofitClient
    ): PexelsApi {
        return retrofitClient.pexelsApi
    }

    // База данных
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Закладки
    @Provides
    @Singleton
    fun provideBookmarkDao(
        database: AppDatabase
    ): BookmarkDao {
        return database.bookmarkDao()
    }

    // репозиторий
    @Provides
    @Singleton
    fun providePhotoRepository(
        api: PexelsApi,
        dao: BookmarkDao,
        networkMonitor: NetworkMonitor
    ): PhotoRepository {
        return PhotoRepositoryImpl(api, dao, networkMonitor)
    }
}