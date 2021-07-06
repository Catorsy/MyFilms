package com.example.myfilms.model.rest.rest_entities

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiUtils {
    private val baseUrlMainPart = "https://api.themoviedb.org/"
    private val baseUrlVersion = "3/movie/"
    val baseUrl = "$baseUrlMainPart$baseUrlVersion"
    val BASE_IMAGE_SITE = "https://image.tmdb.org/t/p/w500"

    fun getOkHTTPBuilderWithHeaders() : OkHttpClient {//OkHttpClient идет в комплекте с ретрофитом
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        httpClient.addInterceptor { chain ->
        val original = chain.request() //к оригинальному реквесту добавляем поля и заново билдим
        val request = original.newBuilder()
            .method(original.method(), original.body())
            .build()
            //можно в .header прописать ключ

            chain.proceed(request)
        }
        return httpClient.build()
    }
}

//"https://api.themoviedb.org/3/movie/${name}?api_key=$MOVIES_KEY&language=ru-RU"