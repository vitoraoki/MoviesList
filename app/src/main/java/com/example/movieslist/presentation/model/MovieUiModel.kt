package com.example.movieslist.presentation.model

import com.example.movieslist.domain.model.Movie

data class MovieUiModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String
) {
    companion object {
        fun mapFromMovie(movie: Movie) = MovieUiModel(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath
        )
    }
}
