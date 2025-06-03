package com.example.movieslist.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object MoviesList : Routes()

    @Serializable
    data class MovieDetails(val id: Int): Routes()
}