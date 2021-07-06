package com.example.myfilms.model.rest.rest_entities

import retrofit2.Call
import retrofit2.http.*


//можно загружать данные синхронно и асинхронно в ретрофите
//"https://api.themoviedb.org/3/movie/${name}?api_key=$MOVIES_KEY&language=ru-RU"

interface MoviesApi {
    @GET("{name}")
    fun getMovie(
        //запрос с изменяемой частью
        @Path("name") id: Int?,
        //аннотация для передаваемых парметров
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call <MoviesDTO>
}

//interface MoviesApi2 {
//    @GET()
//    fun getMovie(
//        @Query("name") id: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Call<MoviesDTO>
//}