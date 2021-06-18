package com.example.myfilms.model

sealed class AppState {

    data class Success(val moviesData: Movies) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}