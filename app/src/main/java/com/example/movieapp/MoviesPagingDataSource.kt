package com.example.movieapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.MoviesData
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Constants.Companion.START_PAGE_INDEX
import java.io.IOException
import javax.inject.Inject

class MoviesPagingDataSource @Inject constructor(val apiService : ApiService) : PagingSource<Int,MoviesData>(){
    override fun getRefreshKey(state: PagingState<Int, MoviesData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesData> {
        val position = params.key ?: START_PAGE_INDEX
        return try {
            val response  = apiService.returnPopularMovies(Constants.API_KEY,position)
            val listCars = response.results
            LoadResult.Page(
                data = listCars as MutableList,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (listCars.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}
