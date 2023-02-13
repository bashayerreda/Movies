package com.example.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.MoviesData
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepo) : ViewModel(){
   fun returnMoviesFromService() : Flow<PagingData<MoviesData>> {
     return moviesRepo.returnMovieData().catch {
            it.message
        }
            .cachedIn(viewModelScope)

    }
}