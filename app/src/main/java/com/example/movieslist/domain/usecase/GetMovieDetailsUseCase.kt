package com.example.movieslist.domain.usecase

import com.example.movieslist.domain.model.MovieDetails
import com.example.movieslist.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: Int): Result<MovieDetails> = repository.getMovieDetails(id)
}