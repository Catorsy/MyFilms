package com.example.myfilms.viewModel

import com.example.myfilms.model.Movies

sealed class AppState {

    data class Success(val moviesData: Movies) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}