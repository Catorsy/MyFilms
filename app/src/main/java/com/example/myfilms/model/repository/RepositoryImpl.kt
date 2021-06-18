package com.example.myfilms.model.repository

import com.example.myfilms.model.Movies

class RepositoryImpl : Repository {
    override fun getMoviesFromServer(): Movies {
        return Movies()
    }

    override fun getMoviesFromLocalStorage(): Movies {
        return Movies()
    }

}