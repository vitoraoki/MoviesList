package com.example.movieslist.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieslist.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MovieDetailsUiState> =
        MutableStateFlow(MovieDetailsUiState.Loading)
    val uiState: Flow<MovieDetailsUiState> = _uiState

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            val result = getMovieDetailsUseCase(id)

            result.fold(
                onSuccess = { movieDetails ->
                    _uiState.value = MovieDetailsUiState.Success(
                        id = movieDetails.id,
                        title = movieDetails.title,
                        overview = movieDetails.overview,
                        posterPath = movieDetails.posterPath,
                        releaseDate = movieDetails.releaseDate,
                        voteAverage = movieDetails.voteAverage
                    )
                },
                onFailure = {
                    _uiState.value = MovieDetailsUiState.Error
                }
            )
        }
    }
}