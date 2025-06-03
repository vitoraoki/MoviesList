package com.example.movieslist.fake

import androidx.paging.PagingSource
import androidx.paging.testing.asPagingSourceFactory
import com.example.movieslist.data.model.MovieResponse
import com.example.movieslist.domain.model.MovieDetails
import com.example.movieslist.domain.repository.MoviesRepository
import io.mockk.mockk

class FakeMoviesRepository : MoviesRepository {
    override fun getMoviesListPagingSource(): PagingSource<Int, MovieResponse> {
        val movieResponses = listOf(
            MovieResponse(
                id = 1,
                title = "title",
                overview = "overview",
                posterPath = "posterPath"
            ),
            MovieResponse(
                id = 2,
                title = "title",
                overview = "overview",
                posterPath = "posterPath"
            )
        )
        val pagingSourceFactory = movieResponses.asPagingSourceFactory()

        return pagingSourceFactory()
    }

    override suspend fun getMovieDetails(id: Int): Result<MovieDetails> = mockk()
}