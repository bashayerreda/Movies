package com.example.movieapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieapp.data.ReturnData
import com.example.movieapp.utils.Constants
import javax.inject.Inject

class MoviesRepo @Inject constructor(private val apiService: ApiService) {
   fun returnMovieData() = Pager(config = PagingConfig(
         pageSize = Constants.NETWORK_PAGE_SIZE,
         maxSize = 15,
         enablePlaceholders = false
      ),
         pagingSourceFactory = { MoviesPagingDataSource(apiService = apiService) }
      ).flow



}