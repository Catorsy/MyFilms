package com.example.myfilms.model.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myfilms.model.Movies
import com.example.myfilms.model.MoviesLoader
import com.example.myfilms.model.getListOfMovies

class RepositoryImpl : Repository {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMoviesFromServer(name: String): Movies {
        val dto = MoviesLoader.loadMovie(name)
        return Movies(
                dto?.fact?.id,
                dto?.fact?.name,
                dto?.fact?.overview,
                dto?.fact?.original_language,
                dto?.fact?.release_date,
                dto?.fact?.vote_average as Double?,
        )
    }

    override fun getMoviesFromLocalStorage() = getListOfMovies()

}