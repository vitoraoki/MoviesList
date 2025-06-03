package com.example.movieslist.presentation.moviedetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.movieslist.R
import com.example.movieslist.presentation.common.ErrorScreen
import com.example.movieslist.presentation.common.LoadingScreen
import com.example.movieslist.presentation.theme.MoviesListTheme

@Composable
fun MovieDetailsScreen(
    innerPadding: PaddingValues,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    id: Int
) {
    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val uiState = viewModel.uiState.collectAsState(MovieDetailsUiState.Loading).value
        when (uiState) {
            is MovieDetailsUiState.Loading -> {
                LoadingScreen()
            }

            is MovieDetailsUiState.Error -> {
                ErrorScreen(errorMessage = stringResource(R.string.movie_details_error_message))
            }

            is MovieDetailsUiState.Success -> {
                MovieDetailsSuccessScreen(uiState)
            }
        }
    }
}

@Composable
private fun MovieDetailsSuccessScreen(
    uiState: MovieDetailsUiState.Success
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = uiState.posterPath,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailRow(
                detailTitle = stringResource(R.string.movie_details_title),
                detailValue = uiState.title
            )

            DetailRow(
                detailTitle = stringResource(R.string.movie_details_overview),
                detailValue = uiState.overview.ifBlank {
                    stringResource(R.string.movie_overview_empty_message)
                }
            )

            DetailRow(
                detailTitle = stringResource(R.string.movie_details_release_date),
                detailValue = uiState.releaseDate
            )

            DetailRow(
                detailTitle = stringResource(R.string.movie_details_vote_average),
                detailValue = uiState.voteAverage.toString()
            )
        }
    }
}

@Composable
private fun DetailRow(
    detailTitle: String,
    detailValue: String,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = detailTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = detailValue,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieDetailsSuccessScreenPreview() {
    MoviesListTheme {
        MovieDetailsSuccessScreen(
            uiState = MovieDetailsUiState.Success(
                id = 1,
                title = "Title",
                overview = "Overview",
                posterPath = "posterPath",
                releaseDate = "releaseDate",
                voteAverage = 1.2
            )
        )
    }
}