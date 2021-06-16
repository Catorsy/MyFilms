package com.example.myfilms.model

sealed class AppState {

    data class Success(val movies: Any) : AppState() //TODO val movies: Movies
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

}