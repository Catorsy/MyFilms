package com.example.myfilms.model.repository

import com.example.myfilms.model.Movies

interface Repository {
    fun getMoviesFromServer(): Movies

    fun getMoviesFromLocalStorage(): List<Movies>
}