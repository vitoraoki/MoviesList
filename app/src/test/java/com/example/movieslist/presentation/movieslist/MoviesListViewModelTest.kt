package com.example.movieslist.presentation.movieslist

import androidx.paging.testing.asSnapshot
import com.example.movieslist.data.api.MoviesListService.Companion.IMAGE_BASE_URL
import com.example.movieslist.fake.FakeGetNowPlayingMoviesUseCase
import com.example.movieslist.presentation.model.MovieUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesListViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun viewModel_assertPagingDataWithMovieUiModel() = runTest {
        val useCase = FakeGetNowPlayingMoviesUseCase()
        val actual = MoviesListViewModel(useCase).movies.asSnapshot()

        val expected = listOf(
            MovieUiModel(
                id = 1,
                title = "title",
                overview = "overview",
                posterPath = IMAGE_BASE_URL + "posterPath"
            ),
            MovieUiModel(
                id = 2,
                title = "title",
                overview = "overview",
                posterPath = IMAGE_BASE_URL + "posterPath"
            )
        )

        assertEquals(expected, actual)
    }
}