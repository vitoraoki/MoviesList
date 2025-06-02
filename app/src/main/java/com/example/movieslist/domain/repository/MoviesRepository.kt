package com.example.movieslist.domain.repository

import androidx.paging.PagingData
import com.example.movieslist.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getNowPlayingMoviesByPage(): Flow<PagingData<Movie>>
}