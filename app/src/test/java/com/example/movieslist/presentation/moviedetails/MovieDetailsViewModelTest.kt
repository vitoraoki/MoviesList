package com.example.movieslist.presentation.moviedetails

import com.example.movieslist.domain.model.MovieDetails
import com.example.movieslist.domain.usecase.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getMovieDetails_verifyGetMovieDetailsUseCaseCall() = runTest {
        coEvery { getMovieDetailsUseCase(any()) } returns Result.failure(Exception())

        val id = 1
        viewModel.getMovieDetails(id)

        coVerify(exactly = 1) {
            getMovieDetailsUseCase(id)
        }
    }

    @Test
    fun viewModel_assertUiStateLoading() = runTest {
        assertEquals(MovieDetailsUiState.Loading, viewModel.uiState.first())
    }

    @Test
    fun getMovieDetails_withResultSuccess_assertUiStateSuccess() = runTest {
        val id = 1
        val movieDetails = MovieDetails(
            id = id,
            title = "title",
            overview = "overview",
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            voteAverage = 0.0
        )
        val result = Result.success(movieDetails)
        coEvery { getMovieDetailsUseCase(id) } returns result

        viewModel.getMovieDetails(id)

        val expected = MovieDetailsUiState.Success(
            id = movieDetails.id,
            title = movieDetails.title,
            overview = movieDetails.overview,
            posterPath = movieDetails.posterPath,
            releaseDate = movieDetails.releaseDate,
            voteAverage = movieDetails.voteAverage
        )

        assertEquals(expected, viewModel.uiState.first())
    }

    @Test
    fun getMovieDetails_withResultError_assertUiStateError() = runTest {
        val id = 1
        coEvery { getMovieDetailsUseCase(id) } returns Result.failure(Exception())

        viewModel.getMovieDetails(id)

        val expected = MovieDetailsUiState.Error

        assertEquals(expected, viewModel.uiState.first())
    }
}