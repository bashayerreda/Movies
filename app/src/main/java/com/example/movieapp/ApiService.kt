package com.example.movieapp

import com.example.movieapp.data.ReturnData
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
  @GET("/movie/popular")
  suspend fun returnPopularMovies(
    @Query("api_key") apiKey: String = Constants.API_KEY,
    @Query("page") page: Int
  ): ReturnData
}