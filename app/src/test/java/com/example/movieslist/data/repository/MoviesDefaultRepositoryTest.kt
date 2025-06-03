package com.example.movieslist.data.repository

import com.example.movieslist.data.api.MoviesListService
import com.example.movieslist.data.model.MovieDetailsResponse
import com.example.movieslist.domain.model.MovieDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesDefaultRepositoryTest {

    private val api: MoviesListService = mockk()
    private val repository = MoviesDefaultRepository(api)

    @Test
    fun getMovieDetails_verifyApiCall() = runTest {
        coEvery { api.getMovieDetails(any()) } returns mockk()

        val id = 1
        repository.getMovieDetails(id)

        coVerify(exactly = 1) {
            api.getMovieDetails(id)
        }
    }

    @Test
    fun getMovieDetails_withSuccessResponse_returnResultWithMovieDetails() = runTest {
        val id = 1
        val movieDetailsResponse = MovieDetailsResponse(
            id = id,
            title = "title",
            overview = "overview",
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            voteAverage = 1.0
        )
        coEvery { api.getMovieDetails(id) } returns movieDetailsResponse

        val actual = repository.getMovieDetails(id)

        val expected = Result.success(
            MovieDetails.fromMovieDetailsResponse(movieDetailsResponse)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun getMovieDetails_withErrorResponse_returnResultWithException() = runTest {
        val id = 1
        val exception = Exception()
        coEvery { api.getMovieDetails(id) } throws exception

        val actual = repository.getMovieDetails(id)

        val expected: Result<Exception> = Result.failure(exception)

        assertEquals(expected, actual)
    }
}