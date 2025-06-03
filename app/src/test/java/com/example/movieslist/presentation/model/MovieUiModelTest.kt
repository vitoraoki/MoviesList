package com.example.movieslist.presentation.model

import com.example.movieslist.domain.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieUiModelTest {

    private val id = 0
    private val title = "title"
    private val overview = "overview"
    private val posterPath = "posterPath"

    @Test
    fun mapFromMovie_returnMovieUiModel() {
        val movie = Movie(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath
        )

        val actual = MovieUiModel.mapFromMovie(movie)

        val expected = MovieUiModel(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath
        )

        assertEquals(expected, actual)
    }
}