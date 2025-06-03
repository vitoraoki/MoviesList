package com.example.movieslist.data.api

import com.example.movieslist.BuildConfig
import com.example.movieslist.data.model.MovieDetailsResponse
import com.example.movieslist.data.model.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesListService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"
    }

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "accept: application/json"
    )
    @GET("movie/now_playing?language=pt-BR")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): NowPlayingResponse

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "accept: application/json"
    )
    @GET("movie/{id}?language=pt-BR")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsResponse
}