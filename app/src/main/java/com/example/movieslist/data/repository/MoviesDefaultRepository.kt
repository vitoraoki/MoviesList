package com.example.movieslist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieslist.data.pagination.MoviesListPagingSource
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesDefaultRepository @Inject constructor(
    private val moviesListPagingSource: MoviesListPagingSource
) : MoviesRepository {

    override fun getNowPlayingMoviesByPage(): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20)) {
            moviesListPagingSource
        }.flow.map { pagingData ->
            pagingData.map { movieResponse ->
                Movie.fromMovieResponse(movieResponse)
            }
        }
}