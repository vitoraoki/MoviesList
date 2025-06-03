package com.example.movieslist.presentation.movieslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieslist.R
import com.example.movieslist.presentation.common.ErrorScreen
import com.example.movieslist.presentation.common.LoadingScreen
import com.example.movieslist.presentation.movieslist.layout.MoviesList

@Composable
fun MoviesListScreen(
    innerPadding: PaddingValues,
    viewModel: MoviesListViewModel = hiltViewModel(),
    onMovieCardClick: (id: Int) -> Unit
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        when(movies.loadState.refresh) {
            is LoadState.Loading -> {
                LoadingScreen()
            }
            is LoadState.Error -> {
                ErrorScreen(
                    errorMessage = stringResource(R.string.movies_list_screen_error_message)
                )
            }
            else -> {
                MoviesList(movies = movies, onMovieCardClick = onMovieCardClick)
            }
        }
    }
}