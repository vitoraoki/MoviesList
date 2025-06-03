package com.example.movieslist.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.domain.usecase.GetNowPlayingMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeGetNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase {
    override fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                FakeMoviesRepository().getMoviesListPagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { movieResponse ->
                Movie.fromMovieResponse(movieResponse)
            }
        }
    }
}