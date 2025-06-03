package com.example.movieslist.presentation.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.movieslist.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.movieslist.presentation.model.MovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
): ViewModel() {

    val movies: Flow<PagingData<MovieUiModel>> = getNowPlayingMoviesUseCase()
        .map { pagingData ->
            pagingData.map { movie ->
                MovieUiModel.mapFromMovie(movie)
            }
        }.cachedIn(viewModelScope)
}