package com.example.movieslist.domain.model

import com.example.movieslist.data.api.MoviesListService.Companion.IMAGE_BASE_URL
import com.example.movieslist.data.model.MovieResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieTest {

    private val id = 1
    private val title = "Title"
    private val overview = "Overview"
    private val posterPath = "posterPath"

    @Test
    fun fromMovieResponse_returnMovie() {
        val response = MovieResponse(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath
        )

        val actual = Movie.fromMovieResponse(response)

        val expected = Movie(
            id = id,
            title = title,
            overview = overview,
            posterPath = IMAGE_BASE_URL + posterPath
        )

        assertEquals(expected, actual)
    }
}