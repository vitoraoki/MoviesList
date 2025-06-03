package com.example.movieslist.domain.repository

import androidx.paging.PagingSource
import com.example.movieslist.data.model.MovieResponse
import com.example.movieslist.domain.model.MovieDetails

interface MoviesRepository {
    fun getMoviesListPagingSource(): PagingSource<Int, MovieResponse>
    suspend fun getMovieDetails(id: Int): Result<MovieDetails>
}