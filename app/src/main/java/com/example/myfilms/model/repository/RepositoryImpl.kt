package com.example.myfilms.model.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myfilms.model.Movies
import com.example.myfilms.model.MoviesLoader
import com.example.myfilms.model.getListOfMovies
import com.example.myfilms.model.rest.rest_entities.MoviesRepo

const val RUSSIAN_LANGUAGE = "ru-RU"

class RepositoryImpl : Repository {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMoviesFromServer(id: Int): Movies {
        val dto = MoviesRepo.api.getMovie(id, MoviesLoader.MOVIES_KEY, RUSSIAN_LANGUAGE)
            .execute().body()
        //.execute не даст выполниться следующей строчке, пока не выполнится предыдущая
        return Movies(
            id = dto?.id,
            title = dto?.title,
            overview = dto?.overview,
            original_language = dto?.original_language,
            release_date = dto?.release_date,
            vote_average = dto?.vote_average,
        )
    }

//    @RequiresApi(Build.VERSION_CODES.N)
//    override fun getMoviesFromServer(id: Int): Movies {
//        val dto = MoviesLoader.loadMovie(id)
//        return Movies(
//                dto?.id,
//                dto?.title,
//                dto?.overview,
//                dto?.original_language,
//                dto?.release_date,
//                dto?.vote_average,
//        )
//    }

    override fun getMoviesFromLocalStorage() = getListOfMovies()

}