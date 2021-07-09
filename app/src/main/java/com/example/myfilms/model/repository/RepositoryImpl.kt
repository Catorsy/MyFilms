package com.example.myfilms.model.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myfilms.model.Movies
import com.example.myfilms.model.MoviesLoader
import com.example.myfilms.model.database.Database
import com.example.myfilms.model.database.History
import com.example.myfilms.model.getListOfMovies
import com.example.myfilms.model.rest.rest_entities.MoviesRepo

const val RUSSIAN_LANGUAGE = "ru-RU"

class RepositoryImpl : Repository {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMoviesFromServer(id: Int): Movies {
        val dto = MoviesRepo.api.getMovie(id, RUSSIAN_LANGUAGE)
            .execute().body()
        //.execute не даст выполниться следующей строчке, пока не выполнится предыдущая
        return Movies(
            id = dto?.id,
            title = dto?.title,
            overview = dto?.overview,
            original_language = dto?.original_language,
            release_date = dto?.release_date,
            vote_average = dto?.vote_average,
            poster_path = dto?.poster_path,
            adult = dto?.adult

        )
    }

    override fun getMoviesFromLocalStorage() = getListOfMovies()

    override fun getAllHistory(): List<Movies> =
        convertHistoryEntityToMovies(Database.db.historyDao().all())

    override fun saveEntity(movies: Movies) {
        Database.db.historyDao().insert(convertMovieToHistoryEntity(movies))
    }

    private fun convertHistoryEntityToMovies(entityList: List <History>) =
        entityList.map {
            Movies(it.idMovie, it.movieName, it.overview, it.original_language, it.year)
        }

    private fun convertMovieToHistoryEntity(movies : Movies) : History =
        History(0, movies.id ?: 0, movies.title ?: "", movies.overview,
            movies.original_language, movies.release_date ?: "")
}



