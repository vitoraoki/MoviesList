package com.example.movieslist.presentation.moviedetails

sealed class MovieDetailsUiState {
    data object Loading : MovieDetailsUiState()
    data object Error : MovieDetailsUiState()
    data class Success(
        val id: Int,
        val title: String,
        val overview: String,
        val posterPath: String,
        val releaseDate: String,
        val voteAverage: Double,
    ) : MovieDetailsUiState()
}