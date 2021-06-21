package com.example.myfilms.model.repository

import com.example.myfilms.model.Movies
import com.example.myfilms.model.getListOfMovies

class RepositoryImpl : Repository {
    override fun getMoviesFromServer(): Movies {
        return Movies()
    }

    override fun getMoviesFromLocalStorage() = getListOfMovies()

}