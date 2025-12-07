package com.example.pexelsapp.di

import com.example.pexelsapp.domain.repository.PhotoRepository
import com.example.pexelsapp.domain.repository.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    companion object {
        @Provides
        @Singleton
        fun providePhotoRepository(
            repositoryImpl: PhotoRepositoryImpl
        ): PhotoRepository {
            return repositoryImpl
        }
    }
}