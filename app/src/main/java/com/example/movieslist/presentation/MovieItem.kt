package com.example.movieslist.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import coil3.compose.AsyncImage
import com.example.movieslist.R
import com.example.movieslist.domain.model.Movie
import com.example.movieslist.presentation.theme.MoviesListTheme

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        shape = RoundedCornerShape(8.dp)
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
private fun MovieDescription(movie: Movie) {
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
        if (movie.overview.isNotBlank()) {
            Text(
                text = movie.overview,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        } else {
            Text(
                text = stringResource(R.string.movie_overview_empty_message),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieItemPreview() {
    MoviesListTheme {
        MovieItem(
            movie = Movie(
                id = 1,
                title = "Title",
                overview = "Overview djsfsdhjgkhjadfhjgkhfdahjgkhjkdhsghjsahgjhsruiehaiuehruighriuehsgjkfdhkjghkreiupbhsuierhjpiugjhpoiwejsilgj;rklajglkajiopsjeoi5gjoisjo;itjilkgjfhiostrpiojhoi;sjkhl;kj;xglfoirdjihoptsjrh",
                posterPath = "path"
            )
        )
    }
}
