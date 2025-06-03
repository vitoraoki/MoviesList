package com.example.movieslist.domain.repository

import androidx.paging.PagingData
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getNowPlayingMoviesByPage(pageSize: Int): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(id: Int): Result<MovieDetails>
}