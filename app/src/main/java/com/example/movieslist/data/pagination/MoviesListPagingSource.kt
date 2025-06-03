package com.example.movieslist.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieslist.data.api.MoviesListService
import com.example.movieslist.data.model.MovieResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class MoviesListPagingSource @Inject constructor(
    private val api: MoviesListService
) : PagingSource<Int, MovieResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getNowPlayingMovies(nextPageNumber)
            return LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}