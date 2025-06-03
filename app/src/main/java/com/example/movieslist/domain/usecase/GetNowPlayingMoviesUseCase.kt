package com.example.movieslist.domain.usecase

import androidx.paging.PagingData
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> = repository.getNowPlayingMoviesByPage(
        pageSize = 20
    )
}