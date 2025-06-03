package com.example.movieslist.domain.model

import com.example.movieslist.data.api.MoviesListService.Companion.IMAGE_BASE_URL
import com.example.movieslist.data.model.MovieDetailsResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsTest {

    private val id = 1
    private val title = "title"
    private val overview = "overview"
    private val posterPath = "posterPath"
    private val releaseDate = "releaseDate"
    private val voteAverage = 1.0

    @Test
    fun fromMovieDetailsResponse_returnMovieDetails() {
        val response = MovieDetailsResponse(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage
        )

        val actual = MovieDetails.fromMovieDetailsResponse(response)

        val expected = MovieDetails(
            id = id,
            title = title,
            overview = overview,
            posterPath = IMAGE_BASE_URL + posterPath,
            releaseDate = releaseDate,
            voteAverage = String.format("%.1f", voteAverage).toDouble()
        )

        assertEquals(expected, actual)
    }
}