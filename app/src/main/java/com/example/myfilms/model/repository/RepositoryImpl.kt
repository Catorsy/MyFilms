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
                dto?.id,
                dto?.name,
                dto?.title,
                dto?.overview,
                dto?.original_language,
                dto?.release_date,
                dto?.vote_average,
        )
    }

    override fun getMoviesFromLocalStorage() = getListOfMovies()

}