package com.example.movieslist.modules

import com.example.movieslist.data.repository.MoviesDefaultRepository
import com.example.movieslist.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMoviesRepository(
        repository: MoviesDefaultRepository
    ): MoviesRepository
}