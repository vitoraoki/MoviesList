package com.example.movieslist.domain.model

import android.annotation.SuppressLint
import com.example.movieslist.data.api.MoviesListService.Companion.IMAGE_BASE_URL
import com.example.movieslist.data.model.MovieDetailsResponse

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
) {
    companion object {

        @SuppressLint("DefaultLocale")
        fun fromMovieDetailsResponse(response: MovieDetailsResponse) = MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            posterPath = IMAGE_BASE_URL + response.posterPath,
            releaseDate = response.releaseDate,
            voteAverage = String.format("%.1f", response.voteAverage).toDouble()
        )
    }
}
