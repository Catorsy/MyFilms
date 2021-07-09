package com.example.myfilms.model.repository

import com.example.myfilms.model.Movies
import com.example.myfilms.model.database.Database
import com.example.myfilms.model.database.History

interface Repository {

    fun getMoviesFromServer(id: Int): Movies
    fun getMoviesFromLocalStorage(): List<Movies>
    fun getAllHistory(): List <Movies>
    fun saveEntity(movies: Movies)
}