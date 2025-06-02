package com.example.movieslist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingResponse(
    val page: Int,
    @SerialName("total_pages") val totalPages: Int,
    val results: List<MovieResponse>
)