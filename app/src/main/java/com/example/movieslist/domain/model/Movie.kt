package com.example.movieslist.domain.model

import com.example.movieslist.data.api.MoviesListService.Companion.IMAGE_BASE_URL
import com.example.movieslist.data.model.MovieResponse

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String
) {
    companion object {
        fun fromMovieResponse(response: MovieResponse) = Movie(
            id = response.id,
            title = response.title,
            overview = response.overview,
            posterPath = IMAGE_BASE_URL + response.posterPath
        )
    }
}
