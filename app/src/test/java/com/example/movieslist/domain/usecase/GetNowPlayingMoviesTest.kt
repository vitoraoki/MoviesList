package com.example.movieslist.domain.usecase

import androidx.paging.testing.asSnapshot
import com.example.movieslist.data.api.MoviesListService.Companion.IMAGE_BASE_URL
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.domain.repository.MoviesRepository
import com.example.movieslist.fake.FakeMoviesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetNowPlayingMoviesTest {

    private val repository: MoviesRepository = FakeMoviesRepository()
    private val useCase = GetNowPlayingMovies(repository)

    @Test
    fun invoke_assertMoviesList() = runTest {
        val actual = useCase().asSnapshot()

        val expected = listOf(
            Movie(
                id = 1,
                title = "title",
                overview = "overview",
                posterPath = IMAGE_BASE_URL + "posterPath"
            ),
            Movie(
                id = 2,
                title = "title",
                overview = "overview",
                posterPath = IMAGE_BASE_URL + "posterPath"
            )
        )

        assertEquals(expected, actual)
    }
}