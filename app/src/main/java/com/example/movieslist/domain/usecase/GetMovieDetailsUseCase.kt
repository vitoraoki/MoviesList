package com.example.movieslist.domain.usecase

import com.example.movieslist.domain.model.MovieDetails
import com.example.movieslist.domain.repository.MoviesRepository
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(id: Int): Result<MovieDetails>
}

class GetMovieDetails @Inject constructor(
    private val repository: MoviesRepository
) : GetMovieDetailsUseCase {
    override suspend operator fun invoke(id: Int): Result<MovieDetails> =
        repository.getMovieDetails(id)
}