package com.example.movieslist.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetNowPlayingMoviesUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

class GetNowPlayingMovies @Inject constructor(
    private val repository: MoviesRepository
) : GetNowPlayingMoviesUseCase {

    override operator fun invoke(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { repository.getMoviesListPagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { movieResponse ->
                Movie.fromMovieResponse(movieResponse)
            }
        }
}