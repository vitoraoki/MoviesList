package com.example.movieslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieslist.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    fun getMovies() = repository.getNowPlayingMoviesByPage().cachedIn(viewModelScope)
}