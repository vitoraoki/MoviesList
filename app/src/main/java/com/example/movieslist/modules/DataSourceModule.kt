package com.example.movieslist.modules

import com.example.movieslist.data.api.MoviesListService
import com.example.movieslist.data.pagination.MoviesListPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun providesMoviesListService(): MoviesListService {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(MoviesListService.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MoviesListService::class.java)
    }

    @Provides
    fun providesMoviesListPagingSource(
        service: MoviesListService
    ): MoviesListPagingSource = MoviesListPagingSource(service)
}