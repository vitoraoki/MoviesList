package com.example.movieslist.domain.usecase

import com.example.movieslist.domain.model.MovieDetails
import com.example.movieslist.domain.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMovieDetailsTest {

    private val repository: MoviesRepository = mockk()
    private val useCase = GetMovieDetails(repository)

    @Test
    fun invoke_verifyGetMovieDetailsCall() = runTest {
        coEvery { repository.getMovieDetails(any()) } returns mockk()

        val id = 1
        useCase(id)

        coVerify(exactly = 1) {
            repository.getMovieDetails(id)
        }
    }

    @Test
    fun invoke_returnResult() = runTest {
        val id = 1
        val movieDetails = MovieDetails(
            id = id,
            title = "title",
            overview = "overview",
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            voteAverage = 0.0
        )
        val expected = Result.success(movieDetails)

        coEvery { repository.getMovieDetails(id) } returns expected

        val actual = useCase(id)

        assertEquals(expected, actual)
    }
}