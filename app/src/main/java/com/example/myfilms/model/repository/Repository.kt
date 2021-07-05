package com.example.myfilms.model.repository

import com.example.myfilms.model.Movies

interface Repository {

    fun getMoviesFromServer(id: Int): Movies
    fun getMoviesFromLocalStorage(): List<Movies>

}