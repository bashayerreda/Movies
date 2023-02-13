package com.example.movieapp.data

import android.os.Parcelable
import java.io.Serializable

data class ReturnData (
    val page : Int? = null,
    val results : MutableList<MoviesData>? = null,
    val total_pages : Int? = null,
    val total_results : Int? = null
) : Serializable