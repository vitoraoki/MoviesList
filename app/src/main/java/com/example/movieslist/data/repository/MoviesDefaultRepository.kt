package com.example.movieslist.data.repository

import androidx.paging.PagingSource
import com.example.movieslist.data.api.MoviesListService
import com.example.movieslist.data.model.MovieResponse
import com.example.movieslist.data.pagination.MoviesListPagingSource
import com.example.movieslist.domain.model.MovieDetails
import com.example.movieslist.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesDefaultRepository @Inject constructor(
    private val api: MoviesListService,
) : MoviesRepository {

    override fun getMoviesListPagingSource(): PagingSource<Int, MovieResponse> =
        MoviesListPagingSource(api)

    override suspend fun getMovieDetails(id: Int): Result<MovieDetails> = runCatching {
        val movieDetailsResponse = api.getMovieDetails(id)
        MovieDetails.fromMovieDetailsResponse(movieDetailsResponse)
    }
}