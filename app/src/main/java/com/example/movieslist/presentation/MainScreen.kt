package com.example.movieslist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieslist.R
import com.example.movieslist.domain.model.Movie

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val movies = viewModel.getMovies().collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when(movies.loadState.refresh) {
            is LoadState.Loading -> {
                LoadingScreen()
            }
            is LoadState.Error -> {
                ErrorScreen()
            }
            else -> {
                SuccessScreen(
                    innerPadding = innerPadding,
                    movies = movies
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(stringResource(R.string.movies_list_screen_error))
    }
}

@Composable
fun SuccessScreen(
    innerPadding: PaddingValues,
    movies: LazyPagingItems<Movie>
) {
    MoviesList(
        modifier = Modifier.padding(innerPadding),
        movies = movies
    )
}