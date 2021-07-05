package com.example.myfilms.model.rest.rest_entities

import com.example.myfilms.model.Movies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Part
import retrofit2.http.Query


//можно загружать данные синхронно и асинхронно в ретрофите
//"https://api.themoviedb.org/3/movie/${name}?api_key=$MOVIES_KEY&language=ru-RU"

interface MoviesApi {
    @GET("movie/{name}")
    fun getMovie(
        //Пат - переменная для замещения конечной точки
        @Part("name") id: Int,
        //квери - аннотация для передаваемых парметров
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MoviesDTO>
}
