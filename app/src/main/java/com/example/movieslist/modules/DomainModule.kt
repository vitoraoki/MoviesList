package com.example.movieslist.modules

import com.example.movieslist.domain.usecase.GetMovieDetails
import com.example.movieslist.domain.usecase.GetMovieDetailsUseCase
import com.example.movieslist.domain.usecase.GetNowPlayingMovies
import com.example.movieslist.domain.usecase.GetNowPlayingMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsGetNowPlayingMoviesUseCase(
        useCase: GetNowPlayingMovies
    ): GetNowPlayingMoviesUseCase

    @Binds
    abstract fun bindsGetMovieDetailsUseCase(
        useCase: GetMovieDetails
    ): GetMovieDetailsUseCase
}