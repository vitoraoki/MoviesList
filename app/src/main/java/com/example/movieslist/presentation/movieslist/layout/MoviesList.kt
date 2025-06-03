package com.example.movieslist.presentation.movieslist.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.example.movieslist.R
import com.example.movieslist.presentation.model.MovieUiModel
import com.example.movieslist.presentation.theme.MoviesListTheme

@Composable
fun MoviesList(
    movies: LazyPagingItems<MovieUiModel>,
    onMovieCardClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically)
    ) {
        items(movies.itemCount) { movieIndex ->
            movies[movieIndex]?.let { movie ->
                MovieItem(movie = movie, onMovieCardClick = onMovieCardClick)
            }
        }

        when (movies.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorItem()
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun LoadingItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(stringResource(R.string.movies_list_loading_more_error))
    }
}

@Composable
private fun MovieItem(
    movie: MovieUiModel,
    onMovieCardClick: (id: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onMovieCardClick(movie.id)
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = null,
            )
            MovieDescription(movie)
        }
    }
}

@Composable
private fun MovieDescription(movie: MovieUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(
            text = movie.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = movie.overview.ifBlank {
                stringResource(R.string.movie_overview_empty_message)
            },
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieItemPreview() {
    MoviesListTheme {
        MovieItem(
            movie = MovieUiModel(
                id = 1,
                title = "Title",
                overview = "Overview",
                posterPath = "path"
            ),
            onMovieCardClick = {}
        )
    }
}